package com.example.shopping.dao.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.CartUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {
    List<CartItem> selectByConsumerId(Long consumerId);

    void updateItemQuantityByCartId(CartUpdateDto dto);

    CartItem selectByCartId(long cartId);
}
