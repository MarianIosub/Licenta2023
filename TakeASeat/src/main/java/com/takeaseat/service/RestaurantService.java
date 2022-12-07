package com.takeaseat.service;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    void saveRestaurant(final CreateRestaurantForm createRestaurantForm);

    boolean hasCurrentUserRestaurantCreated();

    Restaurant getCurrentUserRestaurant();

    List<MenuItem> addMenuItemToRestaurant(Restaurant restaurant, MenuItem menuItem);

    List<MenuItem> searchForMenuItems(String searchedItem);
}
