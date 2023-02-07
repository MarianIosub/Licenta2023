package com.takeaseat.service.impl;

import com.takeaseat.model.Order;
import com.takeaseat.service.OrderService;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.StatisticsService;
import com.takeaseat.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    private final UserService userService;
    private final OrderService orderService;
    private final RestaurantService restaurantService;

    public StatisticsServiceImpl(final UserService userService, final OrderService orderService,
                                 final RestaurantService restaurantService) {
        this.userService = userService;
        this.orderService = orderService;
        this.restaurantService = restaurantService;
    }

    @Override
    public Map<LocalDate, Integer> generateOrdersPerDayStatisticsData() {
        List<Order> lastMonthOrders = getOrderService().getLastMonthOrders(getRestaurantService().getCurrentUserRestaurant());
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.withMonth(initial.getMonthValue() - 1).with(firstDayOfMonth());
        LocalDate end = initial.withDayOfMonth(1);
        Map<LocalDate, Integer> ordersPerDay = new HashMap<>();
        LocalDate iterable = start;
        while (!iterable.isEqual(end)) {
            LocalDate finalIterable = iterable;
            ordersPerDay.put(iterable, (int) lastMonthOrders.stream().filter(o -> o.getDate().isEqual(finalIterable)).count());
            iterable = iterable.plusDays(1);
        }
        return ordersPerDay;
    }

    @Override
    public Map<String, Integer> generateUsersVisitedStatisticsData() {
        List<Order> lastMonthOrders = getOrderService().getLastMonthOrders(getRestaurantService().getCurrentUserRestaurant());
        Map<String, Integer> usersVisited = new HashMap<>();
        for (Order order : lastMonthOrders) {
            if (usersVisited.containsKey(order.getUser().getMail())) {
                usersVisited.put(order.getUser().getMail(), usersVisited.get(order.getUser().getMail()) + 1);
            } else {
                usersVisited.put(order.getUser().getMail(), 1);
            }
        }
        return usersVisited;
    }

    @Override
    public Map<LocalDate, Double> generateIncomePerDayStatisticsData() {
        List<Order> lastMonthOrders = getOrderService().getLastMonthOrders(getRestaurantService().getCurrentUserRestaurant());
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.withMonth(initial.getMonthValue() - 1).with(firstDayOfMonth());
        LocalDate end = initial.withDayOfMonth(1);
        Map<LocalDate, Double> incomePerDay = new HashMap<>();
        LocalDate iterable = start;
        while (!iterable.isEqual(end)) {
            LocalDate finalIterable = iterable;
            double income = lastMonthOrders.stream().filter(o -> o.getDate().isEqual(finalIterable)).mapToDouble(Order::getTotalPrice).sum();
            incomePerDay.put(finalIterable, income);

            iterable = iterable.plusDays(1);
        }
        return incomePerDay;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    protected RestaurantService getRestaurantService() {
        return restaurantService;
    }
}
