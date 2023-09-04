package com.example.shopping.controller.order;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.order.OrderCancelRequestDto;
import com.example.shopping.dto.order.OrderDetailDto;
import com.example.shopping.dto.order.OrderItemDto;
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

    @GetMapping("/order/cancel/{orderSetId}")
    public String getOrderCancelForm(HttpSession httpSession, @PathVariable Long orderSetId, Model model) {
        Consumer consumer = (Consumer) httpSession.getAttribute("login_user");
        if(orderDetailService.getConsumerId(orderSetId) != consumer.getConsumerId()) {
            return "redirect:/";
        }

        model.addAttribute("orderSetId", orderSetId);
        model.addAttribute("orderCancelDtoList", orderDetailService.getOrdersToCancel(orderSetId));

        return "orderCancel";
    }

    // TODO : return type

    /*
     @RequestParam + Model (O)
     => @RequestBody + Model
     => @ModelAttribute
     */
    @PostMapping("/order")
    public String getOrderForm(@RequestParam(value = "orderItemDtoList") String request, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrderItemDto> orderItemDtoList = mapper.readValue(request, new TypeReference<>() {});
        model.addAttribute("orderItemDtoList", orderItemDtoList);

        return "order";
    }
}
