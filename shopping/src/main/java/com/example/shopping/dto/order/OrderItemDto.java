package com.example.shopping.dto.order;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDto implements Serializable {
    private Long itemId;
    private Long cartId;
    private String itemName;
    private long itemQuantity;
    private long itemPrice;
}
