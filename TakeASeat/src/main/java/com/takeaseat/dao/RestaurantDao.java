package com.takeaseat.dao;

import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

public interface RestaurantDao {
    void save(Restaurant restaurant);

    void findByUser(User user);
}