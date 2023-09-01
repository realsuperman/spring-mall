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

    public List<Long> mapToList(Set<Long> set) {
        List<Long> list = new ArrayList<>();
        for(Long ele : set) {
            list.add(ele);
        }
        return list;
    }

    public List<CartItemDto> mapToDto(List<CartItem> foundedCartItem, Set<Long> excludedSet) {
        List<CartItemDto> dtos = new ArrayList<>();
        for(CartItem cartItem : foundedCartItem) {
            long itemId = cartItem.getItemId();
            //hard coding start.
            Long subTotalPricePerItem = 1_000_000 * cartItem.getItemQuantity();
            boolean isExcluded = excludedSet.contains(itemId) ? true : false;
            CartItemDto cartItemDto = CartItemDto.builder()
                    .itemId(itemId)
                    .itemName("갤럭시노트 20")
                    .itemPrice(1_000_000L)
                    .itemQuantity(cartItem.getItemQuantity())
                    .itemImgPaths("https://picsum.photos/90")
                    .subTotalPrice(subTotalPricePerItem)
                    .cartId(cartItem.getCartId())
                    .isExcluded(isExcluded)
                    .build();
            //hard coding end.
            dtos.add(cartItemDto);
        }
        return dtos;
    }
}
