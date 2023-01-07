package com.takeaseat.controller;

import com.takeaseat.model.Order;
import com.takeaseat.service.OrderService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.takeaseat.constants.EndpointsConstants.ORDERS_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.ORDER_CONFIRMATION_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.ORDER_ENDPOINT;
import static com.takeaseat.constants.StringConstants.ORDER;
import static com.takeaseat.constants.StringConstants.ORDERS;
import static com.takeaseat.constants.StringConstants.ORDER_ID;
import static com.takeaseat.constants.ViewsConstants.ERROR_403;
import static com.takeaseat.constants.ViewsConstants.ERROR_404;
import static com.takeaseat.constants.ViewsConstants.ORDERS_VIEW;
import static com.takeaseat.constants.ViewsConstants.ORDER_CONFIRMATION_VIEW;
import static java.util.Objects.isNull;

@Controller
@RequestMapping(ORDER_ENDPOINT)
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

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
    public String getCurrentUserOrders(Model model) {
        final List<Order> orders = getOrderService().getCurrentUserOrders();

        model.addAttribute(ORDERS, orders);

        return ORDERS_VIEW;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    protected UserService getUserService() {
        return userService;
    }
}
