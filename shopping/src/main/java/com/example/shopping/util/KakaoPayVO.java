package com.example.shopping.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KakaoPayVO {
    private String cid;
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    private String pgToken;
    private Integer cancelAmount;
    private Integer cancelTaxFreeAmount;
}