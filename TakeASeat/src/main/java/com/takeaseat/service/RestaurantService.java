package com.takeaseat.service;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.model.Restaurant;

public interface RestaurantService {

    void saveRestaurant(final CreateRestaurantForm createRestaurantForm);

    boolean hasCurrentUserRestaurantCreated();

    Restaurant getCurrentUserRestaurant();
}
