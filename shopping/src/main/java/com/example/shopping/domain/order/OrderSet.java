package com.example.shopping.domain.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderSet {
    private Long orderSetId;
    private Long consumerId;
    private String orderCode;
    private LocalDateTime orderTime;
    private String orderAddress;
    private String orderPhoneNumber;
}
