package com.example.shopping.dto.Category;

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
