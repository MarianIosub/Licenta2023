package com.takeaseat.dao;

import com.takeaseat.model.Order;
import com.takeaseat.model.User;

import java.util.List;

public interface OrderDao {
    Order save(final Order order);

    Order findById(final Long orderId);

    List<Order> getOrdersByUser(final User currentUser);

    void updateOrder(Order order);
}
