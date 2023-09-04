package com.example.shopping.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class KakaoPayCommonProcess {
    public static ResponseEntity<String> getKakaoRestTemplate(String requestUrl, String payloadData) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "KakaoAK 1c2665d0fbd94a38d95ac129fa3d165a");

        HttpEntity<String> requestEntity = new HttpEntity<>(payloadData, httpHeaders);

        return new RestTemplate().exchange(
                requestUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }
}
