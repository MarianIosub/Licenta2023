package com.takeaseat.dao;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import java.util.List;

public interface RestaurantDao {
    void save(Restaurant restaurant);

    Restaurant findByUser(User user);

    void update(Restaurant restaurant);

    List<Restaurant> findAll();

    Restaurant findById(long id);

    List<Restaurant> getRestaurantsByHighestRating();

    List<Restaurant> getRestaurantsByNoOfOrders();

    Restaurant findRestaurantByMenuItem(MenuItem menuItem);
}
