package com.example.shopping.service.cart;

import com.example.shopping.dao.cart.CartDao;
import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.dto.cart.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartDao cartDao;

    public List<CartItem> showByConsumerId(long sessionConsumerId) {
        return cartDao.selectByConsumerId(sessionConsumerId);
    }

    public List<CartItemDto> mapToDto(List<CartItem> cartItems) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            //Item foundItem = itemService.selectItemById(cartItem.getItemId());
            //hard coding start.
            Long subTotalPricePerItem = 1_000_000 * cartItem.getItemQuantity();
            CartItemDto cartItemDto = CartItemDto.builder()
                    .itemId(1L)
                    .itemName("갤럭시노트 20")
                    .itemPrice(1_000_000L)
                    .itemImgPaths("https://picsum.photos/90")
                    .subTotalPrice(subTotalPricePerItem)
                    .itemQuantity(cartItem.getItemQuantity())
                    .cartId(cartItem.getCartId())
                    .build();
            //hard coding end.
            cartItemDtos.add(cartItemDto);
        }
        return cartItemDtos;
    }
}
