package com.example.shopping.controller.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public String showByConsumerId(Model model) {
        long sessionConsumerId = 2L;//hard coding.
        List<CartItem> foundCartItems = cartService.showByConsumerId(sessionConsumerId);
        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundCartItems);
        model.addAttribute("foundItemDtos", foundItemDtos);
        return "cart";
    }
}
