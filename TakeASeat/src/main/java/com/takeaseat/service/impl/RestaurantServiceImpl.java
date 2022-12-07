package com.takeaseat.service.impl;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.converter.Converter;
import com.takeaseat.dao.MenuItemDao;
import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private final RestaurantDao restaurantDao;
    private final UserService userService;
    private final Converter<CreateRestaurantForm, Restaurant> createRestaurantFormRestaurantConverter;
    private final MenuItemDao menuItemDao;

    public RestaurantServiceImpl(final RestaurantDao restaurantDao, final UserService userService,
                                 final Converter<CreateRestaurantForm, Restaurant> createRestaurantFormRestaurantConverter, MenuItemDao menuItemDao) {
        this.restaurantDao = restaurantDao;
        this.userService = userService;
        this.createRestaurantFormRestaurantConverter = createRestaurantFormRestaurantConverter;
        this.menuItemDao = menuItemDao;
    }

    @Override
    public void saveRestaurant(final CreateRestaurantForm createRestaurantForm) {
        Restaurant restaurant = getCreateRestaurantFormRestaurantConverter().convert(createRestaurantForm, Restaurant.class);
        restaurant.setAdministrator(getUserService().getCurrentUser());

        getRestaurantDao().save(restaurant);
    }

    @Override
    public boolean hasCurrentUserRestaurantCreated() {
        try {
            getRestaurantDao().findByUser(getUserService().getCurrentUser());
            return true;
        } catch (NoResultException printed) {
            LOG.warn("Current user has no restaurant created", printed);
            return false;
        }
    }

    @Override
    public Restaurant getCurrentUserRestaurant() {
        return getRestaurantDao().findByUser(getUserService().getCurrentUser());
    }

    @Override
    public List<MenuItem> addMenuItemToRestaurant(Restaurant restaurant, MenuItem menuItem) {
        addMenuItemToRestaurantList(restaurant, menuItem);
        getRestaurantDao().update(restaurant);

        List<MenuItem> menuItems = getMenuItemDao().getMenuItemsByRestaurant(restaurant);
        Collections.reverse(menuItems);
        return menuItems;
    }

    @Override
    public List<MenuItem> searchForMenuItems(String searchedItem) {
        List<MenuItem> menuItems = getCurrentUserRestaurant().getMenuItems();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().contains(searchedItem))
                .collect(Collectors.toList());
    }

    private void addMenuItemToRestaurantList(Restaurant restaurant, MenuItem menuItem) {
        restaurant.getMenuItems().add(menuItem);
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

    protected MenuItemDao getMenuItemDao() {
        return menuItemDao;
    }
}
