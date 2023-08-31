package com.example.shopping.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class Item {
    private Long itemId;
    private Long categoryId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private String itemImagePath;
    private Timestamp itemRegisterTime;
}