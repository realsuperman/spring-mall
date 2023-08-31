package com.example.shopping.dto.cargo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDto {
    private Long itemId;
    private String itemName;
    private Long cnt;
}
