package com.example.shopping.domain.category;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Category {
    private Long categoryId;
    private String categoryName;
    private Long masterCategoryId;


}
