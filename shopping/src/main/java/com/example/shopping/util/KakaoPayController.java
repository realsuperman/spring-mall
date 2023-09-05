package com.example.shopping.util;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.order.OrderCancelRequestDto;
import com.example.shopping.dto.order.OrderRequestDto;
import com.example.shopping.exception.MessageException;
import com.example.shopping.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoPayController {

    private final OrderService orderService;

    @PostMapping
    @ResponseBody
    public String kakaoPayReady(
            @RequestParam String partnerOrderId, @RequestParam String partnerUserId, @RequestParam String itemName,
            @RequestParam String quantity, @RequestParam String totalAmount, @RequestParam String taxFreeAmount
    ) {
        String cid = "TC0ONETIME"; // 가맹점 코드[FIX]

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

        ResponseEntity<String> response = KakaoPayCommonProcess.getKakaoRestTemplate("https://kapi.kakao.com/v1/payment/ready", payloadData);

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new MessageException("결제 응답이 이상함");
        }

        return response.getBody();
    }

    @GetMapping("/{path}")
    public String kakaoPayPopup(@PathVariable String path) {
        log.info("path: "+path);
        return "common/pay";
    }

    @PostMapping("/payment")
    public String kakaoPayApprove(HttpSession httpSession, @RequestBody OrderRequestDto orderRequestDto) throws IOException {
        Consumer consumer = (Consumer) httpSession.getAttribute("login_user");
        Long customerId = consumer.getConsumerId();

        log.info(orderRequestDto.toString());

        orderService.order(customerId, orderRequestDto.getOrderInfoDto(), orderRequestDto.getOrderItemDtoList(), orderRequestDto.getKakaoPayVO());
        return "redirect:/";
    }

    @PutMapping("/cancel")
    public void kakaoPayCancel(@RequestBody OrderCancelRequestDto orderCancelRequestDto) {
        log.info(orderCancelRequestDto.getOrderCancelDtoList().toString());
        log.info(orderCancelRequestDto.getOrderSetId().toString());

        orderService.cancelOrder(orderCancelRequestDto.getOrderSetId(), orderCancelRequestDto.getOrderCancelDtoList());
    }
}