package com.example.shopping.domain.order;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDetail {
    private Long orderDetailId;
    private Long orderSetId;
    private Long cargoId;
    private Long statusId;
    private long buyPrice;
}
