package com.example.shopping.dto.category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRecentRequest {
    private long limit;
    private Long offset;
    private Long categoryId;
}