package com.example.shopping.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class CategoryBestResponse {
    private long itemId;
    private long categoryId;
    private String itemName;
    private long itemPrice;
    private String itemDescription;
    private String itemImagePath;
    private Timestamp itemRegisterTime;
    private Long cnt;
}
