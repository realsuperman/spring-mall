package com.example.shopping.dao.order;

import com.example.shopping.domain.order.OrderDetail;
import com.example.shopping.dto.order.OrderCancelDto;
import com.example.shopping.dto.order.OrderDetailDto;
import com.example.shopping.dto.order.OrderInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDetailDao {
    OrderInfoDto getOrderInfo(Long orderSetId);

    List<OrderDetailDto> getOrderDetailList(Long orderSetId);

    Long getConsumerTotalBuyPrice(Long consumerId);

    Long getConsumerId(Long orderSetId);

    int updateOrderDetailStatusByOrderDetailId(Map<String, Long> map);

    List<Map<String, Long>> getCancelOrderDetailIdAndCargoId(Map<String, Long> map);

    List<OrderCancelDto> getOrdersToCancel(Long orderSetId);

    int insertOrderDetail(List<OrderDetail> list);
}
