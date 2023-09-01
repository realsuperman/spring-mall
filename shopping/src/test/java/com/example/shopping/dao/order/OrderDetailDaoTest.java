package com.example.shopping.dao.order;

import com.example.shopping.domain.order.OrderDetail;
import com.example.shopping.dto.order.OrderCancelDto;
import com.example.shopping.dto.order.OrderDetailDto;
import com.example.shopping.dto.order.OrderInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실 DB 연결
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    final Long testOrderSetId = 2L;
    final Long testConsumerId = 35L;

    @Test
    void getOrderInfo() {
        OrderInfoDto orderInfoDto = orderDetailDao.getOrderInfo(testOrderSetId);

        Assertions.assertEquals("대한민국", orderInfoDto.getOrderAddress());
        Assertions.assertEquals("01012341234", orderInfoDto.getOrderPhoneNumber());
    }

    @Test
    void getOrderDetailList() {
        List<OrderDetailDto> orderDetailDtoList = orderDetailDao.getOrderDetailList(testOrderSetId);

        Assertions.assertEquals(5, orderDetailDtoList.size());
    }

    @Test
    void getConsumerTotalBuyPrice() {
        Long totalBuyPrice = orderDetailDao.getConsumerTotalBuyPrice(testConsumerId);

        Assertions.assertEquals(2_788_169L, totalBuyPrice);
    }

    @Test
    void getConsumerId() {
        Long actual = orderDetailDao.getConsumerId(testOrderSetId);

        Assertions.assertEquals(testConsumerId, actual);
    }

    @Test
    void getCancelOrderDetailIdAndCargoId() {
        Map<String, Long> map = new HashMap<>();
        map.put("orderSetId", testOrderSetId);
        map.put("itemId", 1L);
        map.put("itemQuantity", 2L);

        List<Map<String, Long>> cancelOrderDetailIdAndCargoId = orderDetailDao.getCancelOrderDetailIdAndCargoId(map);

        Assertions.assertEquals(2, cancelOrderDetailIdAndCargoId.size());
    }

    @Test
    void getOrdersToCancel() {
        List<OrderCancelDto> orderCancelDtoList = orderDetailDao.getOrdersToCancel(testOrderSetId);

        Assertions.assertEquals(5, orderCancelDtoList.size());
    }

    @Test
    @Transactional
    void updateOrderDetailStatusByOrderDetailId() {
        Map<String, Long> map = new HashMap<>();
        map.put("statusId", 4L);
        map.put("orderDetailId", 2L);

        int updatedRow = orderDetailDao.updateOrderDetailStatusByOrderDetailId(map);

        Assertions.assertEquals(1, updatedRow);
    }

    @Test
    @Transactional
    void insertOrderDetailSuccess() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            orderDetailList.add(OrderDetail.builder()
                    .orderSetId(testOrderSetId)
                    .cargoId(10L)
                    .statusId(6L)
                    .buyPrice(1000)
                    .build()
            );
        }

        int updatedRows = orderDetailDao.insertOrderDetail(orderDetailList);

        Assertions.assertEquals(5, updatedRows);
    }
}