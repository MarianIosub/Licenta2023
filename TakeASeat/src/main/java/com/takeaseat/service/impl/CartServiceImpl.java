package com.takeaseat.service.impl;

import com.takeaseat.service.CartService;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final RestaurantService restaurantService;

    public CartServiceImpl(final UserService userService, final RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }


    protected UserService getUserService() {
        return userService;
    }

    protected RestaurantService getRestaurantService() {
        return restaurantService;
    }
}
