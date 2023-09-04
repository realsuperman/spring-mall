package com.example.shopping.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class KakaoPayCommonProcess {
    public static HttpURLConnection getKakaoConnection(String requestUrl, String payloadData) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "KakaoAK 1c2665d0fbd94a38d95ac129fa3d165a"); // TODO
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.setDoOutput(true);

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(payloadData);

        return connection;
    }
}
