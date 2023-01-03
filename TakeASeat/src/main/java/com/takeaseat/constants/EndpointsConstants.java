package com.takeaseat.constants;

public interface EndpointsConstants {
    String REDIRECT = "redirect:";

    String REGISTER_ENDPOINT = "/register";
    String HOME_ENDPOINT = "/";
    String UPDATE_PROFILE_ENDPOINT = "/update-profile";
    String LOGIN_ENDPOINT = "/login";
    String ERROR_ENDPOINT = "/error";
    String RESTAURANT_ENDPOINT = "/restaurant";
    String CREATE_RESTAURANT_ENDPOINT = "/create";
    String MANAGE_RESTAURANT_ENPOINT = "/manage";
    String MENU_ITEM = "/menu-item";
    String MENU_ITEM_AVAILABILITY = "/menu-item/availability/{menuItemId}";
    String MENU_ITEM_DELETE = "/menu-item/delete/{menuItemId}";
    String ALL = "/all";
    String CART_ENDPOINT = "/cart";
    String ADD_TO_CART_ENDPOINT = "/add";
    String DELETE_FROM_CART_ENDPOINT = "/delete";
    String INCREASE_QTY_ENDPOINT = "/increase";
    String DECREASE_QTY_CART_ENDPOINT = "/decrease";
}
