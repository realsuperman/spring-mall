package com.example.shopping.controller.order;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.order.OrderDetailDto;
import com.example.shopping.dto.order.OrderItemDto;
import com.example.shopping.dto.order.OrderItemDtoForm;
import com.example.shopping.service.order.OrderDetailService;
import com.example.shopping.service.order.OrderService;
import com.example.shopping.service.order.OrderSetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
@Slf4j
public class OrderController {

    private final OrderDetailService orderDetailService;
    private final OrderSetService orderSetService;
    private final OrderService orderService;

    @GetMapping("/user/my-page/order-set")
    public String getOrderSets(HttpSession httpSession, Model model) {
        Consumer consumer = (Consumer) httpSession.getAttribute("login_user");
        Long consumerId = consumer.getConsumerId();

        model.addAttribute("consumerOrderSetList", orderSetService.getConsumerOrderSetDtoList(consumerId));

        return "orderSetList";
    }

    @GetMapping("/user/my-page/order-set/{orderSetId}")
    public String getOrderDetails(HttpSession httpSession, @PathVariable Long orderSetId, Model model) {
        Consumer consumer = (Consumer) httpSession.getAttribute("login_user");
        if(orderDetailService.getConsumerId(orderSetId) != consumer.getConsumerId()) {
            return "redirect:/";
        }

        model.addAttribute("orderSetId", orderSetId);
        model.addAttribute("orderInfo", orderDetailService.getOrderInfo(orderSetId));

        List<OrderDetailDto> orderDetailList = orderDetailService.getOrderDetailList(orderSetId);

        model.addAttribute("orderDetailList", orderDetailList);
        model.addAttribute("orderSetTotalBuyPrice", orderDetailService.getOrderSetTotalBuyPrice(orderDetailList));

        return "orderDetail";
    }

    /*
     @RequestParam + Model (O)
     => @RequestBody + Model
     => @ModelAttribute
     */
    /*@PostMapping("/order")
    public String getOrderItems(@RequestParam(value = "orderItemDtoList") String request, Model model) throws JsonProcessingException {
        log.info(request);

        ObjectMapper mapper = new ObjectMapper();
        List<OrderItemDto> orderItemDtoList = mapper.readValue(request, new TypeReference<>() {});
        model.addAttribute("orderItemDtoList", orderItemDtoList);

        return "order";
    }*/

    /*@PostMapping("/order")
    public String getOrderItems(@ModelAttribute List<OrderItemDto> orderItemDtoList) {
        log.info(orderItemDtoList.toString());


         *//*ModelAttribute로 List<OrderItemDto>를 바로 받으려 하면
         There was an unexpected error (type=Internal Server Error, status=500).
         No primary or single unique constructor found for interface java.util.List
         java.lang.IllegalStateException: No primary or single unique constructor found for interface java.util.List
        *//*

        return "order";
    }*/

    @PostMapping("/order")
    public String getOrderItems(@ModelAttribute OrderItemDtoForm orderItemDtoList) {
        log.info(orderItemDtoList.toString());

         /*
         There was an unexpected error (type=Bad Request, status=400).
         Validation failed for object='orderItemDtoForm'. Error count: 1
         org.springframework.web.method.annotation.ModelAttributeMethodProcessor$1: org.springframework.validation.BeanPropertyBindingResult: 1 errors
         Field error in object 'orderItemDtoForm' on field 'orderItemDtoList': rejected value [[{"itemId":376,"cartId":1,"itemName":"노트북4","itemQuantity":1,"itemPrice":220002}]];
         codes [typeMismatch.orderItemDtoForm.orderItemDtoList,typeMismatch.orderItemDtoList,typeMismatch.java.util.List,typeMismatch];
         arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [orderItemDtoForm.orderItemDtoList,orderItemDtoList]; arguments [];
         default message [orderItemDtoList]]; default message [Failed to convert value of type 'java.lang.String' to required type 'java.util.List';
         nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'com.example.shopping.dto.order.OrderItemDto': no matching editors or conversion strategy found]
         */

        return "order";
    }

    // (@RequestBody String request)로 받으면 request = "orderItemDtoList=~~"
    /*@PostMapping("/order")
    public String getOrderItems(@RequestBody String request, Model model) throws JsonProcessingException {
        log.info(request); // "orderItemDtoList=%5B%7B%22itemId%22%3A477%2C%22cartId%22%3A1%2C%22itemName%22%3A%22%EA%B8%B0%ED%83%803%22%2C%22itemQuantity%22%3A1%2C%22itemPrice%22%3A321312314%7D%5D"

        ObjectMapper mapper = new ObjectMapper();
        List<OrderItemDto> orderItemDtoList = mapper.readValue(request, new TypeReference<>() {});
        model.addAttribute("orderItemDtoList", orderItemDtoList);

        return "order";
    }*/

    /*@PostMapping("/order")
    public String getOrderItems(@RequestBody String orderItemDtoList, Model model) throws JsonProcessingException {
        log.info(orderItemDtoList);

        *//*
         com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'orderItemDtoList': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
         at [Source: (String)"orderItemDtoList=%5B%7B%22itemId%22%3A98%2C%22cartId%22%3A1%2C%22itemName%22%3A%22%EB%8F%BC%EC%A7%80%EA%B3%A0%EA%B8%B03%22%2C%22itemQuantity%22%3A1%2C%22itemPrice%22%3A20002%7D%5D"; line: 1, column: 17]
         *//*

        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("orderItemDtoList", mapper.readValue(orderItemDtoList, new TypeReference<>() {}));

        return "order";
    }*/

}
