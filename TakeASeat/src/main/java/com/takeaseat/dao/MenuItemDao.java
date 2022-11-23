package com.takeaseat.dao;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> getMenuItemsByRestaurant(Restaurant restaurant);

}
