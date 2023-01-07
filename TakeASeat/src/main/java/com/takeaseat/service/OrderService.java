package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(final Cart cart);

    Order getOrderForId(final Long orderId);

    List<Order> getCurrentUserOrders();
}
