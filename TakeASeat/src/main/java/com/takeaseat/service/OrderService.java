package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.model.Order;

public interface OrderService {
    Order placeOrder(final Cart cart);
}
