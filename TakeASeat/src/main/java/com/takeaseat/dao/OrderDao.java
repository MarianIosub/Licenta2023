package com.takeaseat.dao;

import com.takeaseat.model.Order;
import com.takeaseat.model.User;

import java.util.List;

/**
 * This interface defines the methods for a Order Data Access Object.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface OrderDao {

    /**
     * Save an order.
     *
     * @param order the order to save
     * @return the saved order
     */
    Order save(final Order order);

    /**
     * Find an order by its id.
     *
     * @param orderId the id of the order to find
     * @return the order with the specified id
     */
    Order findById(final Long orderId);

    /**
     * Get all orders for a user.
     *
     * @param currentUser the user to retrieve orders for
     * @return the list of orders for the specified user
     */
    List<Order> getOrdersByUser(final User currentUser);

    /**
     * Update an order.
     *
     * @param order the order to update
     */
    void updateOrder(Order order);
}
