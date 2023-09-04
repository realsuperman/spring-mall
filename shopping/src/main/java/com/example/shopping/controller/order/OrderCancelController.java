package com.example.shopping.controller.order;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.order.OrderCancelDto;
import com.example.shopping.dto.order.OrderCancelRequestDto;
import com.example.shopping.exception.MessageException;
import com.example.shopping.service.order.OrderDetailService;
import com.example.shopping.service.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;


public class OrderCancelController extends HttpServlet {

    private final String fileName = "orderCancel";

    private final OrderDetailService orderDetailService;
    private final OrderService orderService;

    public OrderCancelController(OrderDetailService orderDetailService, OrderService orderService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    private final Logger logger = Logger.getLogger("order cancel controller");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long orderSetId = Long.valueOf(request.getParameter("orderSetId"));

        Consumer consumer = (Consumer) request.getSession().getAttribute("login_user");

        // TODO : Use Validation
        if(orderDetailService.getConsumerId(orderSetId) != consumer.getConsumerId()) {
            response.sendRedirect("/");
            throw new MessageException("유효한 사용자가 아닙니다");
        }

        request.setAttribute("orderSetId", orderSetId);
        request.setAttribute("orderCancelDtoList", orderDetailService.getOrdersToCancel(orderSetId));

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        InputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder requestData = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            requestData.append(line);
        }

        // orderSetId, List<OrderCancelDto> list
        ObjectMapper mapper = new ObjectMapper();

        logger.info("request : "+requestData.toString());

        OrderCancelRequestDto orderCancelRequestDto = mapper.readValue(requestData.toString(), OrderCancelRequestDto.class);

        List<OrderCancelDto> orderCancelDtoList = orderCancelRequestDto.getOrderCancelDtoList();
        Long orderSetId = orderCancelRequestDto.getOrderSetId();
        logger.info("list: "+orderCancelDtoList.toString());
        logger.info("orderSetId: "+orderSetId);
        Consumer consumer = (Consumer) request.getSession().getAttribute("login_user");
        // TODO : Use Validation
        if(orderDetailService.getConsumerId(orderSetId) != consumer.getConsumerId()) {
//            response.sendRedirect("/");
            throw new MessageException("유효한 사용자가 아닙니다");
        }

        orderService.cancelOrder(orderSetId, orderCancelDtoList);
    }
}
