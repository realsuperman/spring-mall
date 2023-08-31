package com.example.shopping.service.cart;

import com.example.shopping.dao.cart.CartDao;
import com.example.shopping.domain.cart.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartDao cartDao;

    public List<CartItem> showByConsumerId(long sessionConsumerId) {
        return cartDao.selectByConsumerId(sessionConsumerId);
    }
}
