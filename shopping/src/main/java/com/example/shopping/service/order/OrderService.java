package com.example.shopping.service.order;

import com.example.shopping.dao.cargo.CargoDao;
import com.example.shopping.dao.cart.CartDao;
import com.example.shopping.dao.order.OrderDetailDao;
import com.example.shopping.dao.order.OrderSetDao;
import com.example.shopping.domain.cargo.Cargo;
import com.example.shopping.domain.order.OrderDetail;
import com.example.shopping.domain.order.OrderSet;
import com.example.shopping.dto.order.OrderCancelDto;
import com.example.shopping.dto.order.OrderInfoDto;
import com.example.shopping.dto.order.OrderItemDto;
import com.example.shopping.exception.MessageException;
import com.example.shopping.util.KakaoPayCancelVO;
import com.example.shopping.util.KakaoPayProcess;
import com.example.shopping.util.KakaoPayVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDetailDao orderDetailDao;
    private final OrderSetDao orderSetDao;
    private final CartDao cartDao;
    private final CargoDao cargoDao;

    private final Long CARGO_STATUS_STOCK = 3L;
    private final Long CARGO_STATUS_RELEASE = 4L;
    private final Long ORDER_STATUS_PAY_COMPLETE = 6L;
    private final Long ORDER_STATUS_ORDER_CANCEL = 7L;

    @Transactional
    public void order(Long consumerId, OrderInfoDto orderInfoDto, List<OrderItemDto> orderItemDtoList, KakaoPayVO kakaoPayVO) {
        try { // kakao 결제 요청 단계 이후, DB 수정 작업과 kakao 결제 승인 단계가 한 트랜잭션으로 묶인다.

            /* TODO
             cargoDao.selectCountByItemId
             cargoDao.selectCargoToDeliver

             그 item_id인 cargo가 원하는 갯수 이상 있니? -> DB 조회
             그 cargo를 원하는 갯수만큼 가져와 -> DB 조회

             =>

             그 item_id인 cargo를 다 가져와 -> DB 조회
             이후 갯수 관련 체크는 service에서?
             */

            throwExceptionIfInsufficientCargo(orderItemDtoList);

            List<Cargo> cargoToDeliver = getCargoToDeliver(orderItemDtoList);

            // update cargo_id.status
            for (Cargo cargo : cargoToDeliver) {
                Map<String, Long> cargoAndStatus = new HashMap<>();
                cargoAndStatus.put("cargoId", cargo.getCargoId());
                cargoAndStatus.put("statusId", CARGO_STATUS_RELEASE);

                cargoDao.updateCargoStatusByCargoId(cargoAndStatus);
            }

            // insert order_set
            OrderSet orderSet = OrderSet.builder()
                    .consumerId(consumerId)
                    .orderCode(kakaoPayVO.getTid()) // 취소를 위해서는 kakao tid 저장해야함
                    .orderAddress(orderInfoDto.getOrderAddress())
                    .orderPhoneNumber(orderInfoDto.getOrderPhoneNumber())
                    .build();
            orderSetDao.insertOrderSet(orderSet);

            // insert order_detail
            List<OrderDetail> orderDetailsToInsert = makeOrderDetailsToInsert(orderSet.getOrderSetId(), orderItemDtoList, cargoToDeliver);
            orderDetailDao.insertOrderDetail(orderDetailsToInsert);

            // delete cart_item
            cartDao.deleteByCartId(orderItemDtoList);

            // kakao 결제 승인 phase
            if (KakaoPayProcess.approve(kakaoPayVO) != HttpServletResponse.SC_OK) {
                throw new MessageException("결제가 실패했습니다");
            }
        } catch (MessageException e) {
            throw e;
        } catch (Exception e) {

        }
    }

    @Transactional
    public void cancelOrder(Long orderSetId, List<OrderCancelDto> orderCancelDtoList) {
        try {
            List<Map<String, Long>> cargoAndOrderDetails = new ArrayList<>();
            // orderSetId, itemId, itemQuantity를 통해 주문 취소할 cargo를 찾는다
            for(OrderCancelDto orderCancelDto: orderCancelDtoList) {
                Map<String, Long> map = new HashMap<>();
                map.put("orderSetId", orderSetId);
                map.put("itemId", orderCancelDto.getItemId());
                map.put("itemQuantity", orderCancelDto.getItemQuantity());
                cargoAndOrderDetails.addAll(orderDetailDao.getCancelOrderDetailIdAndCargoId(map));
            }

            // 각 cargo와 order_detail의 status_id를 수정한다
            for(Map<String, Long> cargoAndOrderDetail: cargoAndOrderDetails) {
                Map<String, Long> cargo = new HashMap<>();
                cargo.put("statusId", CARGO_STATUS_STOCK);
                cargo.put("cargoId", cargoAndOrderDetail.get("cargoId"));
                cargoDao.updateCargoStatusByCargoId(cargo);

                Map<String, Long> orderDetail = new HashMap<>();
                orderDetail.put("orderDetailId", cargoAndOrderDetail.get("orderDetailId"));
                orderDetail.put("statusId", ORDER_STATUS_ORDER_CANCEL);
                orderDetailDao.updateOrderDetailStatusByOrderDetailId(orderDetail);
            }

            long cancelAmount = 0L;
            for(OrderCancelDto orderCancelDto: orderCancelDtoList) {
                cancelAmount += (orderCancelDto.getBuyPrice() * orderCancelDto.getItemQuantity());
            }

            log.info("tid: "+orderSetDao.selectByOrderSetId(orderSetId).getOrderCode());

            KakaoPayCancelVO kakaoPayCancelVO = KakaoPayCancelVO.builder()
                    .tid(orderSetDao.selectByOrderSetId(orderSetId).getOrderCode())
                    .cid("TC0ONETIME")
                    .cancelAmount((int) cancelAmount)
                    .cancelTaxFreeAmount((int) cancelAmount)
                    .build();

            // kakao 결제 취소
            if(KakaoPayProcess.cancel(kakaoPayCancelVO) != HttpServletResponse.SC_OK) {
                throw new MessageException("결제 취소가 실패했습니다");
            }
        } catch (MessageException e) {
            throw e;
        } catch (Exception e) {
            
        }
    }

    // TODO : 동적 쿼리로 DB 한 번만 접근해서 결과 얻을 수 있을 것 같은데
    public void throwExceptionIfInsufficientCargo(List<OrderItemDto> orderItemDtoList) {
        for(OrderItemDto orderItemDto: orderItemDtoList) {
            // item_id를 가지는 cargo의 count가 원하는 갯수 미만이면 exception -> rollback
            if(cargoDao.selectCountByItemId(orderItemDto.getItemId()) < orderItemDto.getItemQuantity()) {
                throw new MessageException("재고가 부족합니다");
            }
        }
    }

    public List<Cargo> getCargoToDeliver(List<OrderItemDto> orderItemDtoList) {
        List<Cargo> cargoToDeliver = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtoList) {
            Map<String, Long> idAndQuantity = new HashMap<>();
            idAndQuantity.put("itemId", orderItemDto.getItemId());
            idAndQuantity.put("itemQuantity", orderItemDto.getItemQuantity());

            cargoToDeliver.addAll(cargoDao.selectCargoToDeliver(idAndQuantity));
        }
        return cargoToDeliver;
    }

    public List<OrderDetail> makeOrderDetailsToInsert(Long orderSetId, List<OrderItemDto> orderItemDtoList, List<Cargo> cargoToDeliver) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtoList) { // item_id buy_price
            for (Cargo cargo : cargoToDeliver) { // cargo_id, item_id
                if (cargo.getItemId().equals(orderItemDto.getItemId())) {
                    orderDetailList.add(OrderDetail.builder()
                            .orderSetId(orderSetId)
                            .buyPrice(orderItemDto.getItemPrice())
                            .cargoId(cargo.getCargoId())
                            .statusId(ORDER_STATUS_PAY_COMPLETE)
                            .build());
                }
            }
        }
        return orderDetailList;
    }
}
