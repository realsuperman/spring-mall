package com.example.shopping.dto.order;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class OrderCancelRequestDto {
    Long orderSetId;
    List<OrderCancelDto> orderCancelDtoList;
}
