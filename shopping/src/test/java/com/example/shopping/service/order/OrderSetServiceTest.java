package com.example.shopping.service.order;

import com.example.shopping.dto.order.OrderSetDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderSetServiceTest {

    @Autowired
    OrderSetService orderSetService;

    final Long testConsumerId = 35L;

    @Test
    void getConsumerOrderSetList() {
        List<OrderSetDto> orderSetDtoList = orderSetService.getConsumerOrderSetDtoList(testConsumerId);

        Assertions.assertEquals(1, orderSetDtoList.size());
    }
}