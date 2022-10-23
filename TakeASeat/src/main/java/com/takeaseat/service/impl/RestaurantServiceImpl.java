package com.takeaseat.service.impl;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.converter.Converter;
import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Restaurant;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDao restaurantDao;
    private final UserService userService;
    private final Converter<CreateRestaurantForm, Restaurant> createRestaurantFormRestaurantConverter;

    public RestaurantServiceImpl(final RestaurantDao restaurantDao, final UserService userService,
                                 final Converter<CreateRestaurantForm, Restaurant> createRestaurantFormRestaurantConverter) {
        this.restaurantDao = restaurantDao;
        this.userService = userService;
        this.createRestaurantFormRestaurantConverter = createRestaurantFormRestaurantConverter;
    }

    @Override
    public void saveRestaurant(final CreateRestaurantForm createRestaurantForm) {
        Restaurant restaurant = getCreateRestaurantFormRestaurantConverter().convert(createRestaurantForm, Restaurant.class);
        restaurant.setAdministrator(getUserService().getCurrentUser());

        getRestaurantDao().save(restaurant);
    }

    protected RestaurantDao getRestaurantDao() {
        return restaurantDao;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected Converter<CreateRestaurantForm, Restaurant> getCreateRestaurantFormRestaurantConverter() {
        return createRestaurantFormRestaurantConverter;
    }
}
