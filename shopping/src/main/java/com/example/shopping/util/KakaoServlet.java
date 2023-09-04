package com.example.shopping.util;

import com.example.shopping.exception.MessageException;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class KakaoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = "TC0ONETIME"; // 가맹점 코드[FIX]
        String partnerOrderId = request.getParameter("partnerOrderId"); // 주문번호
        String partnerUserId = request.getParameter("partnerUserId"); // 회원번호
        String itemName = request.getParameter("itemName"); // 상품명
        int quantity = Integer.parseInt(request.getParameter("quantity")); // 수량
        int totalAmount = Integer.parseInt(request.getParameter("totalAmount")); // 총금액
        int taxFreeAmount = Integer.parseInt(request.getParameter("taxFreeAmount")); // 비과세

        String approvalUrl = "http://localhost/kakao/success?mode=success"; // 결제 성공시 어디로 보낼래?
        String cancelUrl = "http://localhost/kakao/fail?mode=fail"; // 결재 취소시 어디로 보낼래?
        String failUrl = "http://localhost/kakao/fail?mode=fail"; // 결제 실패시 어디로 보낼래?

        String payloadData = "cid=" + cid
                + "&partner_order_id=" + partnerOrderId // order_code
                + "&partner_user_id=" + partnerUserId // sesssion.consumer_id
                + "&item_name=" + itemName // "item_name1,item_name2,item_name3,..."
                + "&quantity=" + quantity // OrderItemDto::itemQuantity.sum
                + "&total_amount=" + totalAmount // (itemQuantity * itemPrice).sum
                + "&tax_free_amount=" + taxFreeAmount // == totalAmount
                + "&approval_url=" + approvalUrl
                + "&cancel_url=" + cancelUrl
                + "&fail_url=" + failUrl;

        HttpURLConnection connection = KakaoPayCommonProcess.getKakaoConnection("https://kapi.kakao.com/v1/payment/ready"
                ,payloadData);

        if(connection.getResponseCode() == HttpStatus.SC_OK){ // 성공
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder rep = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                rep.append(inputLine);
            }
            in.close();

            String jsonResponse = rep.toString();// 받은 응답을 JSON 문자열로 변환하여 할당
            try {
                JSONObject jsonObject = new JSONObject(jsonResponse);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                try (PrintWriter writer = response.getWriter()) {
                    writer.write(jsonObject.toString());
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new MessageException("결제 응답이 이상함");
        }
    }
}