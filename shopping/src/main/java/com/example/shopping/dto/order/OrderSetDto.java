package com.example.shopping.dto.order;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderSetDto {
    private Long orderSetId;
    private String orderCode;
    private String orderAddress;
    private String orderPhoneNumber;
    private String representative;
    private long distinctItemCount;
}
