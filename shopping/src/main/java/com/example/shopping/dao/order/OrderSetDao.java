package com.example.shopping.dao.order;

import com.example.shopping.domain.order.OrderSet;
import com.example.shopping.dto.order.OrderSetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderSetDao {
    // TODO : Map<String, Long> type parameter pagination
    List<OrderSetDto> getConsumerOrderSetDtoList(Long consumerId);

    void insertOrderSet(OrderSet orderSet);

    OrderSet selectByOrderSetId(Long orderSetId);
}
