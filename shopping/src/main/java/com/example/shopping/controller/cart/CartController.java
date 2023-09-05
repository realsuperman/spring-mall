package com.example.shopping.controller.cart;

//import com.example.shopping.domain.cart.CartItem;
//import com.example.shopping.dto.cart.CartItemDto;
//import com.example.shopping.service.cart.CartService;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.domain.cart.CartPageVo;
import com.example.shopping.domain.cart.CartPager;
import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.dto.cart.PutInCartDto;
import com.example.shopping.service.cart.CartService;
import com.example.shopping.service.item.ItemService;
import com.example.shopping.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
//    private final Logger cart_log = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;
    private final ItemService itemService;
    private CartPageVo pageVo;

    @GetMapping
    public String show(HttpSession session, Model model) {
        Consumer consumer = Util.Session.getUser(session);
        long sessionConsumerId = consumer.getConsumerId();

        List<CartItem> foundCartItemAll = cartService.showByConsumerId(sessionConsumerId);
        if(foundCartItemAll.isEmpty()) {
            log.info("foundCartItemAll is null");
            model.addAttribute("errMsg", "장바구니에 담긴 상품이 없습니다.");
        }

        pageVo = new CartPageVo(1, sessionConsumerId);
        List<CartItem> foundCartItems = cartService.showByConsumerIdWithPaging(pageVo);
        CartPager pager = cartService.setUpPaging(pageVo, foundCartItemAll.size());

        //세션에서 excludedSet 가져오기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");

        List<CartItemDto> foundItemDtoAll = cartService.mapToDto(foundCartItemAll, excludedSet);
        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundCartItems, excludedSet);

        model.addAttribute("foundItemDtoAll", foundItemDtoAll);
        model.addAttribute("foundItemDtos", foundItemDtos);
        model.addAttribute("pager", pager);
        return "cart";
    }

    @PostMapping("/insert")
    public String registerItem(@RequestParam(value = "putInCartDto") String jsonString, HttpSession session) {
        System.out.println("jsonString = " + jsonString);
        Consumer consumer = Util.Session.getUser(session);
        long sessionConsumerId = consumer.getConsumerId();
        PutInCartDto putInCartDto = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //JSON 문자열을 Java 객체로 변환
            putInCartDto = objectMapper.readValue(jsonString, PutInCartDto.class);
            //insert logic.
            CartItem cartItem = cartService.checkAlreadyContained(putInCartDto.getItemId(), sessionConsumerId);
            if(cartItem == null) {
                cartService.register(putInCartDto, sessionConsumerId);
            } else {
                long updatedQuantity = cartItem.getItemQuantity() + putInCartDto.getItemQuantity();
                cartService.modifyItemQuantity(cartItem.getCartId(), updatedQuantity);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/itemDetail?itemId=" + putInCartDto.getItemId() + "&success=true";
    }
}
