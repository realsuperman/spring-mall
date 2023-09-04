package com.example.shopping.service.order;

import com.example.shopping.dto.order.OrderCancelDto;
import com.example.shopping.dto.order.OrderDetailDto;
import com.example.shopping.dto.order.OrderInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderDetailServiceTest {

    @Autowired
    OrderDetailService orderDetailService;

    final Long testConsumerId = 35L;
    final Long testOrderSetId = 2L;

    @Test
    void getOrderInfo() {
        OrderInfoDto orderInfoDto = orderDetailService.getOrderInfo(testOrderSetId);

        Assertions.assertEquals("대한민국", orderInfoDto.getOrderAddress());
        Assertions.assertEquals("01012341234", orderInfoDto.getOrderPhoneNumber());
    }

    @Test
    void getOrderDetailList() {
        List<OrderDetailDto> orderDetailDtoList = orderDetailService.getOrderDetailList(testOrderSetId);

        Assertions.assertEquals(5, orderDetailDtoList.size());
    }

    @Test
    void getOrderSetTotalBuyPrice() {
        List<OrderDetailDto> orderDetailDtoList = orderDetailService.getOrderDetailList(testOrderSetId);

        Long orderSetTotalBuyPrice = orderDetailService.getOrderSetTotalBuyPrice(orderDetailDtoList);

        Long totalBuyPrice = orderDetailService.getConsumerTotalBuyPrice(testConsumerId);

        Assertions.assertEquals(orderSetTotalBuyPrice, totalBuyPrice);
    }

    @Test
    void getConsumerId() {
        Long actual = orderDetailService.getConsumerId(testOrderSetId);

        Assertions.assertEquals(testConsumerId, actual);
    }

    @Test
    void getConsumerTotalBuyPrice() {
        Long totalBuyPrice = orderDetailService.getConsumerTotalBuyPrice(testConsumerId);

        Assertions.assertEquals(2_788_169L, totalBuyPrice);
    }

    @Test
    void getOrdersToCancel() {
        List<OrderCancelDto> orderCancelDtoList = orderDetailService.getOrdersToCancel(testOrderSetId);

        Assertions.assertEquals(5, orderCancelDtoList.size());
    }
}