package com.takeaseat.dao;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.util.Set;

public interface MenuItemDao {
    Set<MenuItem> getMenuItemsByRestaurant(Restaurant restaurant);

    Set<MenuItem> getMostOrderedMenuItems();

    void delete(MenuItem menuItem);
}
