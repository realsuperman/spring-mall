package com.example.shopping.controller.order;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.order.OrderInfoDto;
import com.example.shopping.dto.order.OrderItemDto;
import com.example.shopping.service.order.OrderService;
import com.example.shopping.util.KakaoPayVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class PaymentController extends HttpServlet {

    private final OrderService orderService;

    private final Logger logger = Logger.getLogger("PAYMENT CONTROLLER");

    private final String fileName = "payment";

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Consumer consumer = (Consumer) request.getSession().getAttribute("login_user");
        Long customerId = consumer.getConsumerId();

        ObjectMapper objectMapper = new ObjectMapper();
        OrderInfoDto orderInfoDto = objectMapper.readValue(request.getParameter("orderInfoDto"), OrderInfoDto.class);
        logger.info(orderInfoDto.toString());
        List<OrderItemDto> orderItemDtoList = objectMapper.readValue(request.getParameter("orderItemDtoList"), new TypeReference<List<OrderItemDto>>() {
        });
        logger.info(orderItemDtoList.toString());
        KakaoPayVO kakaoPayVO = objectMapper.readValue(request.getParameter("kakaoPayVO"), KakaoPayVO.class);
        logger.info(kakaoPayVO.toString());

        orderService.order(
                customerId,
                orderInfoDto,
                orderItemDtoList,
                kakaoPayVO
        );
        response.sendRedirect("/");
    }
}
