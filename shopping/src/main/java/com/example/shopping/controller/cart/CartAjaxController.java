package com.example.shopping.controller.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.service.cart.CartAjaxService;
import com.example.shopping.service.cart.CartService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/sm/c")
@RequiredArgsConstructor
public class CartAjaxController {
    private final Logger cart_log = LoggerFactory.getLogger(CartAjaxController.class);
    private final CartAjaxService cartAjaxService;
    private final CartService cartService;

    @GetMapping("/api/get")
    public String showComponent(Model model, HttpSession session) {
        cart_log.info("Cart Component here");
        Long sessionConsumerId = 2L;//hard coding.
        List<CartItem> foundedCartItem = cartService.showByConsumerId(sessionConsumerId);
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");

        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundedCartItem, excludedSet);
        model.addAttribute("foundItemDtos", foundItemDtos);
        return "cart_component";
    }
    @PostMapping("/api/v1")
    public String addUnchecked(@RequestBody Map<String, Object> requestData, HttpSession session) {
        int excludedItemIdInt = (Integer)requestData.get("excludedItemId");
        long excludedItemId = Long.valueOf(excludedItemIdInt);
        cart_log.info("excludedItemId: " + Long.valueOf(excludedItemId));

        //HashSet에 uncheck된 itemId 담기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
        Set<Long> updatedSet = cartAjaxService.addExcludedItemId(excludedItemId, excludedSet);
        //세션에 업데이트된 HashSet 저장
        session.setAttribute("excludedSet", updatedSet);

        return "redirect:/sm/c/api/get";
    }

    @PostMapping("/api/v2")
    public String removeUnchecked(@RequestBody Map<String, Object> requestData, HttpSession session) {
        int includedItemIdInt = (Integer)requestData.get("includedItemId");
        long includedItemId = Long.valueOf(includedItemIdInt);
        //HashSet에 check된 itemId 빼기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
        excludedSet.remove(includedItemId);
        //세션에 업데이트된 HashSet 저장
        session.setAttribute("excludedSet", excludedSet);
        return "redirect:/sm/c/api/get";
    }
}
