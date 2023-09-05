package com.example.shopping.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderInfoDto {
    private LocalDateTime orderTime;
    private String orderCode;
    private String orderAddress;
    private String orderPhoneNumber;
}
