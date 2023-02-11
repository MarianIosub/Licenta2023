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
	String MANAGE_RESTAURANT_ENDPOINT = "/manage";
	String MENU_ITEM = "/menu-item";
	String MENU_ITEM_AVAILABILITY = "/menu-item/availability/{menuItemId}";
	String MENU_ITEM_DELETE = "/menu-item/delete/{menuItemId}";
	String ALL = "/all";
	String CART_ENDPOINT = "/cart";
	String ADD_TO_CART_ENDPOINT = "/add";
	String DELETE_FROM_CART_ENDPOINT = "/delete";
	String INCREASE_QTY_ENDPOINT = "/increase";
	String DECREASE_QTY_CART_ENDPOINT = "/decrease";
	String CHECKOUT_ENDPOINT = "/checkout";
	String CHARGE_ENDPOINT = "/charge";
	String DATE_CART_ENDPOINT = "/date";
	String START_CART_ENDPOINT = "/start";
	String END_CART_ENDPOINT = "/end";
	String ORDER_ENDPOINT = "/order";
	String ORDER_CONFIRMATION_ENDPOINT = "/confirmation/{orderId}";
	String ORDERS_ENDPOINT = "/orders";
	String RESERVATIONS_ENDPOINT = "/reservations";
	String RESERVATIONS_LIST_ENDPOINT = "/reservations/filter";
	String RESERVATIONS_ACCEPT_ENDPOINT = "/reservations/accept";
	String RESERVATIONS_REFUSE_ENDPOINT = "/reservations/refuse";
	String ORDERS_LIST_ENDPOINT = "/orders/filter";
	String REVIEW_ENDPOINT = "/review";
	String FORGOT_PASSWORD_ENDPOINT = "/forgot-password";
	String STATISTICS_ENDPOINT = "/statistics";
	String ORDERS_PER_DAY_ENDPOINT = "/orders-per-day";
	String USERS_VISITED = "/users-visited";
	String INCOME_PER_DAY_ENDPOINT = "/income-per-day";
}
