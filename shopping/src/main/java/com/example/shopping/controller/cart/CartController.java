package com.example.shopping.controller.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity showByConsumerId() {
        long sessionConsumerId = 2L;
        List<CartItem> foundCartItems = cartService.showByConsumerId(sessionConsumerId);
        return ResponseEntity.ok(foundCartItems);
    }
}
