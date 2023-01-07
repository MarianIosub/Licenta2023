package com.takeaseat.service.impl;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.converter.CartToOrderConverter;
import com.takeaseat.dao.OrderDao;
import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Order;
import com.takeaseat.model.Restaurant;
import com.takeaseat.service.OrderService;
import com.takeaseat.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final RestaurantDao restaurantDao;
    private final CartToOrderConverter cartToOrderConverter;
    private final UserService userService;

    public OrderServiceImpl(final OrderDao orderDao, final CartToOrderConverter cartToOrderConverter,
                            final RestaurantDao restaurantDao, final UserService userService) {
        this.orderDao = orderDao;
        this.cartToOrderConverter = cartToOrderConverter;
        this.restaurantDao = restaurantDao;
        this.userService = userService;
    }

    @Override
    public Order placeOrder(Cart cart) {
        Order order = getCartToOrderConverter().convert(cart);
        increaseRestaurantOrders(cart);

        return getOrderDao().save(order);
    }

    @Override
    public Order getOrderForId(final Long orderId) {
        return getOrderDao().findById(orderId);
    }

    @Override
    public List<Order> getCurrentUserOrders() {
        return getOrderDao().getOrdersByUser(getUserService().getCurrentUser());
    }

    private void increaseRestaurantOrders(final Cart cart) {
        Restaurant restaurant = cart.getRestaurant();
        restaurant.setNoOfReservations(restaurant.getNoOfReservations() + 1);
        restaurant.getMenuItems().stream()
                .filter(m -> cart.getMenuItems().containsKey(m))
                .forEach(m -> m.setNoOfOrders(m.getNoOfOrders() + cart.getMenuItems().get(m)));
        getRestaurantDao().update(restaurant);
    }

    protected OrderDao getOrderDao() {
        return orderDao;
    }

    protected RestaurantDao getRestaurantDao() {
        return restaurantDao;
    }

    protected CartToOrderConverter getCartToOrderConverter() {
        return cartToOrderConverter;
    }

    protected UserService getUserService() {
        return userService;
    }
}
