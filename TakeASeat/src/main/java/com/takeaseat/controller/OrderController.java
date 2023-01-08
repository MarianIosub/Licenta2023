package com.takeaseat.controller;

import com.takeaseat.model.Order;
import com.takeaseat.service.OrderService;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static com.takeaseat.constants.EndpointsConstants.ORDERS_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.ORDERS_LIST_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.ORDER_CONFIRMATION_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.ORDER_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.REDIRECT;
import static com.takeaseat.constants.EndpointsConstants.RESERVATIONS_ACCEPT_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.RESERVATIONS_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.RESERVATIONS_LIST_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.RESERVATIONS_REFUSE_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.REVIEW_ENDPOINT;
import static com.takeaseat.constants.StringConstants.CURRENT_RESTAURANT;
import static com.takeaseat.constants.StringConstants.CURRENT_USER;
import static com.takeaseat.constants.StringConstants.MESSAGE;
import static com.takeaseat.constants.StringConstants.ORDER;
import static com.takeaseat.constants.StringConstants.ORDERS;
import static com.takeaseat.constants.StringConstants.ORDER_ID;
import static com.takeaseat.constants.StringConstants.ORDER_STATUS;
import static com.takeaseat.constants.StringConstants.RATING;
import static com.takeaseat.constants.StringConstants.TODAY;
import static com.takeaseat.constants.ViewsConstants.ERROR_403;
import static com.takeaseat.constants.ViewsConstants.ERROR_404;
import static com.takeaseat.constants.ViewsConstants.ORDERS_LIST_VIEW;
import static com.takeaseat.constants.ViewsConstants.ORDERS_VIEW;
import static com.takeaseat.constants.ViewsConstants.ORDER_CONFIRMATION_VIEW;
import static com.takeaseat.constants.ViewsConstants.RESERVATIONS_LIST_VIEW;
import static com.takeaseat.constants.ViewsConstants.RESERVATIONS_VIEW;
import static com.takeaseat.helper.CreateEndpointHelper.createEndpoint;
import static java.util.Objects.isNull;

@Controller
@RequestMapping(ORDER_ENDPOINT)
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @RequestMapping(ORDER_CONFIRMATION_ENDPOINT)
    public String getOrderConfirmationPage(@PathVariable(ORDER_ID) Long orderId, Model model) {
        Order order = getOrderService().getOrderForId(orderId);
        if (isNull(order)) {
            return ERROR_404;
        }
        if (!order.getUser().equals(getUserService().getCurrentUser())) {
            return ERROR_403;
        }
        model.addAttribute(ORDER, order);

        return ORDER_CONFIRMATION_VIEW;
    }

    @RequestMapping(ORDERS_ENDPOINT)
    public String getCurrentUserOrders(Model model, @RequestParam(value = ORDER_STATUS, required = false, defaultValue = "Waiting") String orderStatus) {
        final List<Order> orders = getOrderService().getCurrentUserOrders(orderStatus);
        model.addAttribute(ORDERS, orders);
        model.addAttribute(CURRENT_USER, getUserService().getCurrentUser());
        model.addAttribute(TODAY, LocalDate.now());
        return ORDERS_VIEW;
    }

    @RequestMapping(ORDERS_LIST_ENDPOINT)
    public String getCurrentUserOrdersFiltered(Model model, @RequestParam(value = ORDER_STATUS, required = false, defaultValue = "Waiting") String orderStatus) {
        final List<Order> orders = getOrderService().getCurrentUserOrders(orderStatus);
        model.addAttribute(ORDERS, orders);
        model.addAttribute(CURRENT_USER, getUserService().getCurrentUser());
        model.addAttribute(TODAY, LocalDate.now());
        return ORDERS_LIST_VIEW;
    }


    @RequestMapping(RESERVATIONS_ENDPOINT)
    public String getCurrentAdministratorReservations(Model model) {
        final List<Order> orders = getOrderService().getCurrentUserOrders("Waiting");
        model.addAttribute(ORDERS, orders);
        model.addAttribute(CURRENT_RESTAURANT, getRestaurantService().getCurrentUserRestaurant());

        return RESERVATIONS_VIEW;
    }

    @RequestMapping(RESERVATIONS_LIST_ENDPOINT)
    public String getCurrentAdministratorReservations(Model model,
                                                      @RequestParam(value = ORDER_STATUS, required = false, defaultValue = "Waiting") String orderStatus) {
        final List<Order> orders = getOrderService().getCurrentUserOrders(orderStatus);
        model.addAttribute(ORDERS, orders);

        return RESERVATIONS_LIST_VIEW;
    }

    @RequestMapping(value = RESERVATIONS_ACCEPT_ENDPOINT, method = RequestMethod.POST)
    public String acceptOrder(@RequestParam(ORDER_ID) Long orderId,
                              @RequestParam(MESSAGE) String message) {
        getOrderService().acceptOrder(orderId, message);

        return createEndpoint(REDIRECT, ORDER_ENDPOINT, RESERVATIONS_LIST_ENDPOINT);
    }

    @RequestMapping(value = RESERVATIONS_REFUSE_ENDPOINT, method = RequestMethod.POST)
    public String refuseOrder(@RequestParam(ORDER_ID) Long orderId,
                              @RequestParam(MESSAGE) String message) {
        getOrderService().refuseOrder(orderId, message);

        return createEndpoint(REDIRECT, ORDER_ENDPOINT, RESERVATIONS_LIST_ENDPOINT);
    }

    @RequestMapping(value = REVIEW_ENDPOINT, method = RequestMethod.POST)
    public String reviewRestaurant(@RequestParam(ORDER_ID) Long orderId, @RequestParam(MESSAGE) String message, @RequestParam(RATING) Integer rating) {
        getOrderService().placeReview(orderId, message, rating);

        return createEndpoint(REDIRECT, ORDER_ENDPOINT, ORDERS_LIST_ENDPOINT);
    }

    protected RestaurantService getRestaurantService() {
        return restaurantService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    protected UserService getUserService() {
        return userService;
    }
}
