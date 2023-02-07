package com.takeaseat.service.impl;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.converter.CartToOrderConverter;
import com.takeaseat.dao.OrderDao;
import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Order;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.Review;
import com.takeaseat.service.OrderService;
import com.takeaseat.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.takeaseat.constants.StringConstants.APPROVED;
import static com.takeaseat.constants.StringConstants.FUTURE;
import static com.takeaseat.constants.StringConstants.PAST;
import static com.takeaseat.constants.StringConstants.UNAPPROVED;
import static com.takeaseat.constants.StringConstants.WAITING;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
    public List<Order> getCurrentUserOrders(String orderStatus) {
        List<Order> orders = getOrderDao().getOrdersByUser(getUserService().getCurrentUser());
        if (isNull(orderStatus)) {
            return orders;
        }

        switch (orderStatus.toLowerCase()) {
            case APPROVED:
                return orders.stream().filter(o -> nonNull(o.getApproved()) && o.getApproved()).collect(Collectors.toList());
            case UNAPPROVED:
                return orders.stream().filter(o -> nonNull(o.getApproved()) && !o.getApproved()).collect(Collectors.toList());
            case WAITING:
                return orders.stream().filter(o -> isNull(o.getApproved())).collect(Collectors.toList());
            case PAST:
                return orders.stream().filter(o -> o.getDate().isBefore(LocalDate.now())).collect(Collectors.toList());
            case FUTURE:
                return orders.stream().filter(o -> o.getDate().isAfter(LocalDate.now())).collect(Collectors.toList());
            default:
                return orders;
        }
    }

    @Override
    public void acceptOrder(Long orderId, String message) {
        Order order = getOrderForId(orderId);
        order.setApproved(Boolean.TRUE);
        order.setMessage(message);
        getOrderDao().updateOrder(order);
    }

    @Override
    public void refuseOrder(Long orderId, String message) {
        Order order = getOrderForId(orderId);
        order.setApproved(Boolean.FALSE);
        order.setMessage(message);
        getOrderDao().updateOrder(order);
    }

    @Override
    public void placeReview(Long orderId, String message, Integer rating) {
        Order order = getOrderForId(orderId);
        Restaurant restaurant = order.getRestaurant();
        Review review = Review.builder()
                .grade(rating)
                .comment(message)
                .user(order.getUser().getName() + " " + order.getUser().getSurname())
                .localDate(order.getDate())
                .build();
        restaurant.addReview(review);
        calculateRestaurantRating(restaurant);
        order.setReviewed(true);
        getRestaurantDao().update(restaurant);
        getOrderDao().updateOrder(order);
    }

    @Override
    public List<Order> getLastMonthOrders(Restaurant currentUserRestaurant) {
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.withMonth(initial.getMonthValue()-1).with(firstDayOfMonth());
        LocalDate end = initial.withMonth(initial.getMonthValue()-1).with(lastDayOfMonth());
        return getCurrentUserOrders(PAST).stream()
                .filter(o -> o.getDate().isAfter(start))
                .filter(o -> o.getDate().isBefore(end))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
    }

    private void calculateRestaurantRating(Restaurant restaurant) {
        double rating = 0.0d;
        for (Review review : restaurant.getReviews()) {
            rating = rating + review.getGrade();
        }
        restaurant.setRating(rating / restaurant.getReviews().size());
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
