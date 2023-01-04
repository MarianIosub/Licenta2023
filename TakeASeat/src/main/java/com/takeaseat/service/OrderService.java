package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;

public interface OrderService {
    void placeOrder(final Cart cart);
}
