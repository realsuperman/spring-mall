package com.example.shopping.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CategoryRecentResponse {
    private Long itemId;
    private Long itemPrice;
    private String itemName;
    private String itemImagePath;
}
