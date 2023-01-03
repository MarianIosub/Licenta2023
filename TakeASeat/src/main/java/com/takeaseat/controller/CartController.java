package com.takeaseat.controller;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.takeaseat.constants.EndpointsConstants.ADD_TO_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.DECREASE_QTY_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.DELETE_FROM_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.INCREASE_QTY_ENDPOINT;
import static com.takeaseat.constants.StringConstants.CART;
import static com.takeaseat.constants.StringConstants.MENU_ITEM_ID;
import static com.takeaseat.constants.ViewsConstants.RESERVATION_COMPONENT;

@Controller
@RequestMapping(CART_ENDPOINT)
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @RequestMapping(value = ADD_TO_CART_ENDPOINT, method = RequestMethod.POST)
    public String addToCart(@SessionAttribute(CART) Cart cart, @RequestParam(MENU_ITEM_ID) Long menuItemId) {
        getCartService().addToCart(cart, menuItemId);

        return RESERVATION_COMPONENT;
    }

    @RequestMapping(value = DELETE_FROM_CART_ENDPOINT, method = RequestMethod.POST)
    public String deleteFromCart(@SessionAttribute(CART) Cart cart, @RequestParam(MENU_ITEM_ID) Long menuItemId) {
        getCartService().deleteFromCart(cart, menuItemId);

        return RESERVATION_COMPONENT;
    }

    @RequestMapping(value = INCREASE_QTY_ENDPOINT, method = RequestMethod.POST)
    public String increaseQuantity(@SessionAttribute(CART) Cart cart, @RequestParam(MENU_ITEM_ID) Long menuItemId) {
        getCartService().increaseQuantityForMenuItem(cart, menuItemId);

        return RESERVATION_COMPONENT;
    }

    @RequestMapping(value = DECREASE_QTY_CART_ENDPOINT, method = RequestMethod.POST)
    public String decreaseQuantity(@SessionAttribute(CART) Cart cart, @RequestParam(MENU_ITEM_ID) Long menuItemId) {
        getCartService().decreaseQuantityForMenuItem(cart, menuItemId);

        return RESERVATION_COMPONENT;
    }

    protected CartService getCartService() {
        return cartService;
    }
}
