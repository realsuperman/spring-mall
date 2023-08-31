package com.example.shopping.dao.cart;

import com.example.shopping.domain.cart.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {
    List<CartItem> selectByConsumerId(Long consumerId);
}
