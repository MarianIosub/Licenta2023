package com.takeaseat.service.impl;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.converter.CartToOrderConverter;
import com.takeaseat.dao.OrderDao;
import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Order;
import com.takeaseat.model.Restaurant;
import com.takeaseat.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final RestaurantDao restaurantDao;
    private final CartToOrderConverter cartToOrderConverter;

    public OrderServiceImpl(final OrderDao orderDao, final CartToOrderConverter cartToOrderConverter, final RestaurantDao restaurantDao) {
        this.orderDao = orderDao;
        this.cartToOrderConverter = cartToOrderConverter;
        this.restaurantDao = restaurantDao;
    }

    @Override
    public Order placeOrder(Cart cart) {
        Order order = cartToOrderConverter.convert(cart);
        increaseRestaurantOrders(cart);

        return orderDao.save(order);
    }

    private void increaseRestaurantOrders(final Cart cart) {
        Restaurant restaurant = cart.getRestaurant();
        restaurant.setNoOfReservations(restaurant.getNoOfReservations() + 1);
        restaurant.getMenuItems().stream()
                .filter(m -> cart.getMenuItems().containsKey(m))
                .forEach(m -> m.setNoOfOrders(m.getNoOfOrders() + cart.getMenuItems().get(m)));
        restaurantDao.update(restaurant);
    }


}
