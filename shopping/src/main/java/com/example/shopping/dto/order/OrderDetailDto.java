package com.example.shopping.dto.order;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDetailDto {
    private String itemName;
    private long itemQuantity;
    private long buyPrice;
    private String statusName;
}
