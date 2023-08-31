package com.example.shopping.dao.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

@Mapper
public interface OrderDetailDao {

    public Long getConsumerTotalBuyPrice(Long consumerId);

}
