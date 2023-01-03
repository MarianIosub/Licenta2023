package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;

public interface CartService {
    Cart addToCart(Cart cart, Long menuItemId);

    Cart calculateCartTotal(Cart cart);

    Cart deleteFromCart(Cart cart, Long menuItemId);

    void increaseQuantityForMenuItem(Cart cart, Long menuItemId);

    void decreaseQuantityForMenuItem(Cart cart, Long menuItemId);
}
