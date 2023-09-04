package com.example.shopping.dto.order;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDtoForm implements Serializable {
    private List<OrderItemDto> orderItemDtoList;
}
