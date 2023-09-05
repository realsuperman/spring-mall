package com.example.shopping.domain.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartCheckVo {
    private Long itemId;
    private Long consumerId;
}
