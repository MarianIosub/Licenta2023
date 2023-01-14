package com.takeaseat.service;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RestaurantService {

    void saveRestaurant(final CreateRestaurantForm createRestaurantForm) throws IOException;

    boolean hasCurrentUserRestaurantCreated();

    Restaurant getCurrentUserRestaurant();

    Set<MenuItem> addMenuItemToRestaurant(Restaurant restaurant, MenuItem menuItem);

    Set<MenuItem> searchForMenuItems(final String searchedItem);

    void changeMenuItemAvailability(Long menuItemId);

    void deleteMenuItem(Long menuItemId);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> searchForRestaurant(String searchedItem, String sortOption);

    Restaurant getRestaurantById(String restaurantId);

    Set<MenuItem> getMenuItemsForRestaurant(String restaurantId, String searchedItem, String sortOption);

    boolean hasAvailableItems(Set<MenuItem> menuItems);

    boolean hasUnavailableItems(Set<MenuItem> menuItems);

    List<Restaurant> getMostRatedRestaurants();

    List<Restaurant> getMostOrderedRestaurants();

    Map<MenuItem, Restaurant> getMostOrderedMenuItems();
}
