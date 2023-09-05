package com.example.shopping.dao.cart;

import com.example.shopping.domain.cart.*;

import com.example.shopping.domain.cart.CartItem;

import com.example.shopping.dto.order.OrderItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {
    List<CartItem> selectListByConsumeId(Long consumerId);
    CartItem selectByVo(CartSelectVo vo);
    List<CartItem> selectListWithPaging(CartPageVo vo);
    void updateItemQuantityByCartId(CartUpdateVo vo);
    void deleteByCartId(List<OrderItemDto> orderItemDtoList);
    void deleteItemByCartId(Long cartId);
    void insert(CartInsertVo vo);
}
