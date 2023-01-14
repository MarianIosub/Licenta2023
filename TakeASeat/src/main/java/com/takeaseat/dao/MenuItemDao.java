package com.takeaseat.dao;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.util.Set;

/**
 * This interface defines the methods for a MenuItem Data Access Object.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface MenuItemDao {

    /**
     * Retrieves the menu items for a specified restaurant.
     *
     * @param restaurant the restaurant to retrieve menu items for
     * @return the set of menu items for the specified restaurant
     */
    Set<MenuItem> getMenuItemsByRestaurant(Restaurant restaurant);

    /**
     * Retrieves the most ordered menu items.
     *
     * @return the set of the most ordered menu items
     */
    Set<MenuItem> getMostOrderedMenuItems();

    /**
     * Deletes a menu item.
     *
     * @param menuItem the menu item to delete
     */
    void delete(MenuItem menuItem);
}
