package com.example.shopping.dao.order;

import com.example.shopping.domain.order.OrderSet;
import com.example.shopping.dto.order.OrderSetDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderSetDaoTest {

    @Autowired
    OrderSetDao orderSetDao;

    final Long testOrderSetId = 2L;
    final Long testConsumerId = 35L;
    final String testAddress = "TEST_ADDRESS";
    final String testCode = "TEST_ORDER_CODE";
    final String testPhoneNumber = "TEST_PHONE_NUMBER";

    @Test
    @Transactional
    void insertOrderSet() {
        OrderSet testOrderSet = OrderSet.builder()
                .consumerId(testConsumerId)
                .orderAddress(testAddress)
                .orderCode(testCode)
                .orderPhoneNumber(testPhoneNumber)
                .build();

        Assertions.assertNull(testOrderSet.getOrderSetId());

        orderSetDao.insertOrderSet(testOrderSet);

        Assertions.assertNotNull(testOrderSet.getOrderSetId());
    }

    @Test
    void getConsumerOrderSetDtoList() {
        List<OrderSetDto> orderSetDtoList = orderSetDao.getConsumerOrderSetDtoList(testConsumerId);

        Assertions.assertEquals(1, orderSetDtoList.size());
    }

    @Test
    void selectByOrderSetId() {
        OrderSet orderSet = orderSetDao.selectByOrderSetId(testOrderSetId);

        Assertions.assertEquals(testConsumerId, orderSet.getConsumerId());
    }
}