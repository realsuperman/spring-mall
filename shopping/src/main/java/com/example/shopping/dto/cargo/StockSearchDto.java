package com.example.shopping.dto.cargo;

import lombok.Setter;

@Setter
public class StockSearchDto {
    String itemName;
    Long pageSize;
    Long offset;
}
