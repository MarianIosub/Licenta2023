package com.takeaseat.controller;

import com.stripe.exception.StripeException;
import com.takeaseat.controller.dto.Cart;
import com.takeaseat.controller.dto.ChargeRequest;
import com.takeaseat.model.Order;
import com.takeaseat.service.CartService;
import com.takeaseat.service.OrderService;
import com.takeaseat.service.PaymentService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.takeaseat.constants.EndpointsConstants.ADD_TO_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.CHARGE_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.CHECKOUT_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.DATE_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.DECREASE_QTY_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.DELETE_FROM_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.END_CART_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.INCREASE_QTY_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.START_CART_ENDPOINT;
import static com.takeaseat.constants.StringConstants.AMOUNT;
import static com.takeaseat.constants.StringConstants.CART;
import static com.takeaseat.constants.StringConstants.CURRENCY;
import static com.takeaseat.constants.StringConstants.MENU_ITEM_ID;
import static com.takeaseat.constants.StringConstants.ORDER;
import static com.takeaseat.constants.StringConstants.RESERVATION_DATE;
import static com.takeaseat.constants.StringConstants.RESERVATION_END;
import static com.takeaseat.constants.StringConstants.RESERVATION_START;
import static com.takeaseat.constants.StringConstants.STRIPE_API_PUBLIC_KEY;
import static com.takeaseat.constants.StringConstants.STRIPE_EMAIL;
import static com.takeaseat.constants.StringConstants.STRIPE_PUBLIC_KEY;
import static com.takeaseat.constants.StringConstants.STRIPE_TOKEN;
import static com.takeaseat.constants.ViewsConstants.CHECKOUT_CONFIRMATION_VIEW;
import static com.takeaseat.constants.ViewsConstants.CHECKOUT_VIEW;
import static com.takeaseat.constants.ViewsConstants.ERROR_DEFAULT;
import static com.takeaseat.constants.ViewsConstants.RESERVATION_COMPONENT;

@Controller
@RequestMapping(CART_ENDPOINT)
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final UserService userService;

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

    @RequestMapping(value = DATE_CART_ENDPOINT, method = RequestMethod.POST)
    public String setCartDate(@SessionAttribute(CART) Cart cart, @RequestParam(RESERVATION_DATE) String date) {
        getCartService().setCartDate(cart, date);

        return RESERVATION_COMPONENT;
    }

    @RequestMapping(value = START_CART_ENDPOINT, method = RequestMethod.POST)
    public String setCartStart(@SessionAttribute(CART) Cart cart, @RequestParam(RESERVATION_START) String start) {
        getCartService().setCartReservationStart(cart, start);

        return RESERVATION_COMPONENT;
    }

    @RequestMapping(value = END_CART_ENDPOINT, method = RequestMethod.POST)
    public String setCartEnd(@SessionAttribute(CART) Cart cart, @RequestParam(RESERVATION_END) String end) {
        getCartService().setCartReservationEnd(cart, end);

        return RESERVATION_COMPONENT;
    }

    @RequestMapping(value = CHECKOUT_ENDPOINT, method = RequestMethod.GET)
    public String checkout(Model model) {
        model.addAttribute(STRIPE_PUBLIC_KEY, STRIPE_API_PUBLIC_KEY);
        model.addAttribute(CURRENCY, ChargeRequest.Currency.RON);
        return CHECKOUT_VIEW;
    }

    @RequestMapping(value = CHARGE_ENDPOINT, method = RequestMethod.POST)
    public String charge(HttpServletRequest request, @SessionAttribute(CART) Cart cart,
                         HttpSession session, Model model) throws StripeException {
        ChargeRequest chargeRequest = getChargeRequest(request);
        cart.setCharge(getPaymentService().charge(chargeRequest));
        Order order = getOrderService().placeOrder(cart);
        model.addAttribute(ORDER, order);
        session.removeAttribute(CART);
        return CHECKOUT_CONFIRMATION_VIEW;
    }


    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute(ERROR_DEFAULT, ex.getMessage());
        return CHECKOUT_CONFIRMATION_VIEW;
    }

    private ChargeRequest getChargeRequest(HttpServletRequest request) {
        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount((int) (Double.parseDouble(request.getParameter(AMOUNT)) * 100));
        chargeRequest.setCurrency(ChargeRequest.Currency.RON);
        chargeRequest.setStripeEmail(request.getParameter(STRIPE_EMAIL));
        chargeRequest.setStripeToken(request.getParameter(STRIPE_TOKEN));

        return chargeRequest;
    }

    protected CartService getCartService() {
        return cartService;
    }

    protected PaymentService getPaymentService() {
        return paymentService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    protected UserService getUserService() {
        return userService;
    }
}
