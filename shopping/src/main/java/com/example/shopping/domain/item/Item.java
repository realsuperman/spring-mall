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
    @NotNull(message = "상세 카테고리는 비어 있을 수 없습니다.")
    private Long categoryId;
    @NotBlank(message = "제품명은 비어 있을 수 없습니다.")
    @Size(max=127, message = "제품명의 최대 사이즈는 127자 입니다")
    private String itemName;
    @NotNull(message = "제품 가격이 비어 있을 수 없습니다.")
    @Min(value=1, message = "제품 가격의 최소값은 1 이상입니다")
    @Max(value=1000000, message = "제품 가격의 최대값은 1000000입니다.")
    private Long itemPrice;
    @NotBlank(message = "제품 설명은 비어 있을 수 없습니다.")
    @Size(max=512, message = "제품 설명의 최대 사이즈는 512자 입니다")
    private String itemDescription;
    private String itemImagePath;
    private Timestamp itemRegisterTime;

    /**
     * 아래 항목들은 검증을 위한 항목들임
     */
    @NotBlank(message = "섬네일은 비어 있을 수 없습니다")
    @Size(max=170, message = "섬네일이 이상합니다")
    private String image1Name;
    @NotBlank(message = "이미지1은 비어 있을 수 없습니다")
    @Size(max=170, message = "이미지2가 이상합니다")
    private String image2Name;
    @NotBlank(message = "이미지2는 비어 있을 수 없습니다")
    @Size(max=170, message = "이미지2가 이상합니다")
    private String image3Name;
    @NotBlank(message = "이미지3은 비어 있을 수 없습니다")
    @Size(max=170, message = "이미지3이 이상합니다")
    private String image4Name;
    @NotBlank(message = "이미지4는 비어 있을 수 없습니다")
    @Size(max=170, message = "이미지4가 이상합니다")
    private String image5Name;
    @NotBlank(message = "이미지5는 비어 있을 수 없습니다")
    @Size(max=170, message = "이미지5가 이상합니다")
    private String image6Name;
    @Min(value=1, message = "수량의 최소 값은 1 이상입니다")
    @Max(value=100, message = "수량의 최대 값은 100입니다")
    private Integer itemQuantity;
}
