package com.example.shopping.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartUpdateDto {
    private Long cartId;
    private Long itemQuantity;
}
