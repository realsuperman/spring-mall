package com.example.shopping.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDto {
    private Long itemId;
    private Long cartId;
    private String itemName;
    private long itemQuantity;
    private long itemPrice;
}
