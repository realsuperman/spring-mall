package com.example.shopping.util;

public class KakaoPayProcess {
    public static int approve(KakaoPayVO kakaoPayVO) {
        String payloadData = "cid=" + kakaoPayVO.getCid()
                + "&tid=" + kakaoPayVO.getTid()
                + "&partner_order_id=" + kakaoPayVO.getPartnerOrderId()
                + "&partner_user_id=" + kakaoPayVO.getPartnerUserId()
                + "&pg_token=" + kakaoPayVO.getPgToken();
        return KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/approve", payloadData).getStatusCode().value();
    }

    public static int cancel(KakaoPayCancelVO kakaoPayCancelVO) {
        String payloadData = "cid=" + kakaoPayCancelVO.getCid()
                + "&tid=" + kakaoPayCancelVO.getTid()
                + "&cancel_amount=" + kakaoPayCancelVO.getCancelAmount()
                + "&cancel_tax_free_amount=" + kakaoPayCancelVO.getCancelTaxFreeAmount();
        return KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/cancel", payloadData).getStatusCode().value();
    }
}