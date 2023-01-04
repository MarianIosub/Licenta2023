package com.takeaseat.service.impl;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OrderServiceImpl implements OrderService {


    @Override
    public void placeOrder(Cart cart) {
    }
}
