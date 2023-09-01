package com.example.shopping.service.order;

import com.example.shopping.dao.order.OrderSetDao;
import com.example.shopping.dto.order.OrderSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSetService {
    private final OrderSetDao orderSetDao;

    public List<OrderSetDto> getConsumerOrderSetDtoList(Long consumerId) {
        return orderSetDao.getConsumerOrderSetDtoList(consumerId);
    }
}
