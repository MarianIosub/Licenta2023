package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.model.Order;
import com.takeaseat.model.Restaurant;

import java.util.List;

/**
 * This interface defines the methods for an order service.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface OrderService {

    /**
     * Places an order based on the items in a cart.
     *
     * @param cart the cart to place an order for
     * @return the created order
     */
    Order placeOrder(final Cart cart);

    /**
     * Retrieves an order by its id.
     *
     * @param orderId the id of the order to retrieve
     * @return the order with the specified id
     */
    Order getOrderForId(final Long orderId);

    /**
     * Retrieves the current user's orders based on the specified order status.
     *
     * @param orderStatus the status of the orders to retrieve
     * @return the list of orders matching the specified status
     */
    List<Order> getCurrentUserOrders(final String orderStatus);

    /**
     * Accepts an order with a message.
     *
     * @param orderId the id of the order to accept
     * @param message the message to include with the acceptance
     */
    void acceptOrder(final Long orderId, final String message);

    /**
     * Refuses an order with a message.
     *
     * @param orderId the id of the order to refuse
     * @param message the message to include with the refusal
     */
    void refuseOrder(final Long orderId, final String message);

    /**
     * Places a review on an order with a message and a rating.
     *
     * @param orderId the id of the order to place a review on
     * @param message the message to include in the review
     * @param rating  the rating to include in the review
     */
    void placeReview(final Long orderId, final String message, final Integer rating);

    List<Order> getLastMonthOrders(final Restaurant currentUserRestaurant);
}
