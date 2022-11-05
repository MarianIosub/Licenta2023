package com.takeaseat.service;

import com.takeaseat.controller.form.CreateRestaurantForm;

public interface RestaurantService {

    void saveRestaurant(final CreateRestaurantForm createRestaurantForm);

    boolean hasCurrentUserRestaurantCreated();
}
