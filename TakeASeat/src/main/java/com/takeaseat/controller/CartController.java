package com.takeaseat.controller;

import com.takeaseat.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.takeaseat.constants.EndpointsConstants.CART_ENDPOINT;

@Controller
@RequestMapping(CART_ENDPOINT)
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    protected CartService getCartService() {
        return cartService;
    }
}
