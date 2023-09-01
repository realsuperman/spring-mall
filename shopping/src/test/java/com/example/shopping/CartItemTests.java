package com.example.shopping;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.dto.cart.CartUpdateDto;
import com.example.shopping.service.cart.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartItemTests {
    @Autowired
    private CartService cartService;
    @Test
    @DisplayName("cart_id로 수량 변경")
    public void test_1() {
        long itemQuantity = 5L;
        long cartId = 1L;
        CartItem foundCartItem = cartService.showByCartId(cartId);
        CartUpdateDto cartUpdateDto = new CartUpdateDto(cartId, itemQuantity);
        cartService.modifyItemQuantity(cartUpdateDto);
    }
}
