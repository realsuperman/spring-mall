package com.example.shopping.util;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoPayCancelVO {
    private String cid;
    private String tid;
    private Integer cancelAmount;
    private Integer cancelTaxFreeAmount;
}
