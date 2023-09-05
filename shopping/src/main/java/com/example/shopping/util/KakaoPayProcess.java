package com.example.shopping.util;

import com.example.shopping.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class KakaoPayProcess {
    public static int approve(KakaoPayVO kakaoPayVO) {
        String payloadData = "cid=" + kakaoPayVO.getCid()
                + "&tid=" + kakaoPayVO.getTid()
                + "&partner_order_id=" + kakaoPayVO.getPartnerOrderId()
                + "&partner_user_id=" + kakaoPayVO.getPartnerUserId()
                + "&pg_token=" + kakaoPayVO.getPgToken();

        ResponseEntity<String> result = null;
        try {
            result = KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/approve", payloadData);
        } catch (Exception e) {
            log.error(e.getCause().getMessage());
        }
        return result.getStatusCode().value();
    }

    public static int cancel(KakaoPayCancelVO kakaoPayCancelVO) {
        String payloadData = "cid=" + kakaoPayCancelVO.getCid()
                + "&tid=" + kakaoPayCancelVO.getTid()
                + "&cancel_amount=" + kakaoPayCancelVO.getCancelAmount()
                + "&cancel_tax_free_amount=" + kakaoPayCancelVO.getCancelTaxFreeAmount();

        ResponseEntity<String> result = null;
        try {
            result = KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/cancel", payloadData);
        } catch (Exception e) {
            log.error(e.getCause().getMessage());
        }
        return result.getStatusCode().value();
    }
}