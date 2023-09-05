package com.example.shopping.util;

import com.example.shopping.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public class KakaoPayProcess {
    public static int approve(KakaoPayVO kakaoPayVO) {
        String payloadData = "cid=" + kakaoPayVO.getCid()
                + "&tid=" + kakaoPayVO.getTid()
                + "&partner_order_id=" + kakaoPayVO.getPartnerOrderId()
                + "&partner_user_id=" + kakaoPayVO.getPartnerUserId()
                + "&pg_token=" + kakaoPayVO.getPgToken();

        try {
            return KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/approve", payloadData).getStatusCode().value();
        } catch (Exception e) {
            log.error(e.getCause().getMessage());
            throw new MessageException("결제 요청 오류");
        }
    }

    public static int cancel(KakaoPayCancelVO kakaoPayCancelVO) {
        String payloadData = "cid=" + kakaoPayCancelVO.getCid()
                + "&tid=" + kakaoPayCancelVO.getTid()
                + "&cancel_amount=" + kakaoPayCancelVO.getCancelAmount()
                + "&cancel_tax_free_amount=" + kakaoPayCancelVO.getCancelTaxFreeAmount();

        try {
            return KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/cancel", payloadData).getStatusCode().value();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MessageException("결제 취소 오류");
        }
    }
}