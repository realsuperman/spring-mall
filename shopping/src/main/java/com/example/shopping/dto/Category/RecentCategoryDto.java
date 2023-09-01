package com.example.shopping.dto.Category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecentCategoryDto {
    private long limit;
    private Long offset;
    private Long categoryId;

}
