package com.takeaseat.service;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {

    void saveRestaurant(final CreateRestaurantForm createRestaurantForm) throws IOException;

    boolean hasCurrentUserRestaurantCreated();

    Restaurant getCurrentUserRestaurant();

    List<MenuItem> addMenuItemToRestaurant(Restaurant restaurant, MenuItem menuItem);

    List<MenuItem> searchForMenuItems(final String searchedItem);

    void changeMenuItemAvailability(Long menuItemId);

    void deleteMenuItem(Long menuItemId);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> searchForRestaurant(String searchedItem, String sortOption);

    Restaurant getRestaurantById(String restaurantId);

    List<MenuItem> getMenuItemsForRestaurant(String restaurantId, String searchedItem, String sortOption);

    boolean hasAvailableItems(List<MenuItem> menuItems);

    boolean hasUnavailableItems(List<MenuItem> menuItems);
}
