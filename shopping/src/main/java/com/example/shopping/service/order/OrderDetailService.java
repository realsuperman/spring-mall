package com.example.shopping.service.order;

import com.example.shopping.dao.order.OrderDetailDao;
import com.example.shopping.dto.order.OrderCancelDto;
import com.example.shopping.dto.order.OrderDetailDto;
import com.example.shopping.dto.order.OrderInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderDetailService {

    private final OrderDetailDao orderDetailDao;

    /**
     * 특정 order_set_id에 해당하는 배송지 주소와 배송지 연락처를 dto 형태로 반환
     * @param orderSetId
     * @return OrderAddressInfoDto
     */
    public OrderInfoDto getOrderInfo(Long orderSetId) {
        return orderDetailDao.getOrderInfo(orderSetId);
    }

    /**
     * 특정 order_set_id에 해당하는 order_detail들의 상품명, 갯수, 구매 당시 가격, 상태 이름을 dto형태로 List에 담아 반환
     * @param orderSetId
     * @return List<OrderDetailDto>
     */
    public List<OrderDetailDto> getOrderDetailList(Long orderSetId) {
        return orderDetailDao.getOrderDetailList(orderSetId);
    }

    /**
     * orderset의 총 결제 금액을 반환
     * @param orderDetailDtoList
     * @return 총 결제 금액
     */
    // TODO : 하드 코딩 수정
    public long getOrderSetTotalBuyPrice(List<OrderDetailDto> orderDetailDtoList) {
        long result = 0L;
        for(OrderDetailDto orderDetailDto: orderDetailDtoList) {
            if(orderDetailDto.getStatusName().equals("취소") || orderDetailDto.getStatusName().equals("반품")) continue;
            result += orderDetailDto.getBuyPrice() * orderDetailDto.getItemQuantity();
        }
        return result;
    }

    public long getConsumerId(Long orderSetId) {
        return orderDetailDao.getConsumerId(orderSetId);
    }

    public long getConsumerTotalBuyPrice(Long consumerId) {
        return orderDetailDao.getConsumerTotalBuyPrice(consumerId);
    }

    public List<OrderCancelDto> getOrdersToCancel(Long orderSetId) {
        return orderDetailDao.getOrdersToCancel(orderSetId);
    }
}
