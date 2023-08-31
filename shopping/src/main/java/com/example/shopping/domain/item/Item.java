package com.example.shopping.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@ToString
public class Item {
    private Long itemId;
    @NotNull(message = "{item.categoryId.notNull}")
    private Long categoryId;
    @NotBlank(message = "{item.itemName.notNull}")
    @Size(max=127, message = "{item.itemName.maxSize}")
    private String itemName;
    @NotNull(message = "{item.itemPrice.notNull}")
    @Min(value=1, message = "{item.itemPrice.min}")
    @Max(value=1000000, message = "{item.itemPrice.max}")
    private Long itemPrice;
    @NotBlank(message = "{item.itemPrice.notNull}")
    @Size(max=512, message = "{item.itemDescription.maxSize}")
    private String itemDescription;
    private String itemImagePath;
    private Timestamp itemRegisterTime;

    /**
     * 아래 항목들은 검증을 위한 항목들임
     */
    @NotBlank(message = "{item.image1.notNull}")
    @Size(max=170, message = "{item.image1.maxSize}")
    private String image1name;
    @NotBlank(message = "{item.image2.notNull}")
    @Size(max=170, message = "{item.image2.maxSize}")
    private String image2name;
    @NotBlank(message = "{item.image3.notNull}")
    @Size(max=170, message = "{item.image3.maxSize}")
    private String image3name;
    @NotBlank(message = "{item.image4.notNull}")
    @Size(max=170, message = "{item.image4.maxSize}")
    private String image4name;
    @NotBlank(message = "{item.image5.notNull}")
    @Size(max=170, message = "{item.image5.maxSize}")
    private String image5name;
    @NotBlank(message = "{item.image6.notNull}")
    @Size(max=170, message = "{item.image6.maxSize}")
    private String image6name;
    @Min(value=1, message = "{item.itemQuantity.min}")
    @Max(value=100, message = "{item.itemQuantity.max}")
    private int itemQuantity;
}
