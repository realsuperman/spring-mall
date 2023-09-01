package com.example.shopping.service.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.CartItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartAjaxService {
    private final Logger cart_log = LoggerFactory.getLogger(CartAjaxService.class);
    public Set<Long> addExcludedItemId(Long excludedItemId, Set<Long> excludedSet) {
        if(excludedSet == null) {
            excludedSet = new HashSet<>();
        }
        Set<Long> hs = new HashSet<>(excludedSet);
        hs.add(excludedItemId);
        return hs;
    }
}
