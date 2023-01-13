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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private final RestaurantDao restaurantDao;
    private final UserService userService;
    private final Converter<CreateRestaurantForm, Restaurant> createRestaurantFormRestaurantConverter;
    private final MenuItemDao menuItemDao;

    public RestaurantServiceImpl(final RestaurantDao restaurantDao, final UserService userService,
                                 final Converter<CreateRestaurantForm, Restaurant> createRestaurantFormRestaurantConverter,
                                 final MenuItemDao menuItemDao) {
        this.restaurantDao = restaurantDao;
        this.userService = userService;
        this.createRestaurantFormRestaurantConverter = createRestaurantFormRestaurantConverter;
        this.menuItemDao = menuItemDao;
    }

    @Override
    public void saveRestaurant(final CreateRestaurantForm createRestaurantForm) throws IOException {
        Restaurant restaurant = getCreateRestaurantFormRestaurantConverter().convert(createRestaurantForm, Restaurant.class);
        restaurant.setAdministrator(getUserService().getCurrentUser());
        restaurant.setImage(new String(Base64.getEncoder().encode(
                createRestaurantForm.getPhoto().getBytes()), StandardCharsets.UTF_8));

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
    public List<MenuItem> searchForMenuItems(final String searchedItem) {
        List<MenuItem> menuItems = getCurrentUserRestaurant().getMenuItems();

        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().toLowerCase().contains(searchedItem.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void changeMenuItemAvailability(Long menuItemId) {
        Restaurant restaurant = getCurrentUserRestaurant();

        restaurant.getMenuItems().stream()
                .filter(menuItem -> menuItem.getId().equals(menuItemId))
                .forEach(menuItem -> menuItem.setAvailable(!menuItem.isAvailable()));

        getRestaurantDao().update(restaurant);
    }

    @Override
    public void deleteMenuItem(Long menuItemId) {
        Restaurant restaurant = getCurrentUserRestaurant();

        restaurant.getMenuItems().stream()
                .filter(menuItem -> menuItem.getId().equals(menuItemId))
                .findFirst()
                .ifPresent(menuItem -> {
                    restaurant.getMenuItems().remove(menuItem);
                    getMenuItemDao().delete(menuItem);
                    getRestaurantDao().update(restaurant);
                });

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return getRestaurantDao().findAll();
    }

    @Override
    public List<Restaurant> searchForRestaurant(String searchedItem, String sortOption) {
        List<Restaurant> searchedRestaurants = getAllRestaurants().stream()
                .filter(r -> r.getName().toLowerCase().contains(searchedItem.toLowerCase()))
                .collect(Collectors.toList());

        if (sortOption == null) {
            return searchedRestaurants;
        }

        switch (sortOption) {
            case "ALPHABETICAL":
                searchedRestaurants.sort(Comparator.comparing(Restaurant::getName));
                break;
            case "INVERSE":
                searchedRestaurants.sort(Comparator.comparing(Restaurant::getName));
                Collections.reverse(searchedRestaurants);
                break;
            case "POPULAR":
                searchedRestaurants.sort(Comparator.comparing(Restaurant::getNoOfReservations));
                Collections.reverse(searchedRestaurants);
                break;
            case "RATING":
                searchedRestaurants.sort(Comparator.comparingDouble(Restaurant::getRating));
                Collections.reverse(searchedRestaurants);
                break;
            default:
                break;
        }
        return searchedRestaurants;
    }

    @Override
    public Restaurant getRestaurantById(String restaurantId) {
        return getRestaurantDao().findById(Long.parseLong(restaurantId));
    }

    @Override
    public List<MenuItem> getMenuItemsForRestaurant(String restaurantId, String searchedItem, String sortOption) {
        Restaurant restaurant = getRestaurantById(restaurantId);

        List<MenuItem> menuItems = restaurant.getMenuItems().stream()
                .filter(m -> m.getName().toLowerCase().contains(searchedItem.toLowerCase()))
                .collect(Collectors.toList());

        if (sortOption == null) {
            return menuItems;
        }

        switch (sortOption) {
            case "ALPHABETICAL":
                menuItems.sort((Comparator.comparing(MenuItem::getName)));
                break;
            case "INVERSE":
                menuItems.sort((Comparator.comparing(MenuItem::getName)));
                Collections.reverse(menuItems);
                break;
            case "POPULAR":
                menuItems.sort(Comparator.comparing(MenuItem::getNoOfOrders));
                Collections.reverse(menuItems);
                break;
            default:
                break;
        }
        return menuItems;
    }

    @Override
    public boolean hasAvailableItems(List<MenuItem> menuItems) {
        return menuItems.stream().anyMatch(MenuItem::isAvailable);
    }

    @Override
    public boolean hasUnavailableItems(List<MenuItem> menuItems) {
        return !menuItems.stream().allMatch(MenuItem::isAvailable);
    }

    @Override
    public List<Restaurant> getMostRatedRestaurants() {
        return getRestaurantDao().getRestaurantsByHighestRating();
    }

    @Override
    public List<Restaurant> getMostOrderedRestaurants() {
        return getRestaurantDao().getRestaurantsByNoOfOrders();
    }

    @Override
    public Map<MenuItem, Restaurant> getMostOrderedMenuItems() {
        List<MenuItem> menuItems = getMenuItemDao().getMostOrderedMenuItems();
        Map<MenuItem, Restaurant> menuItemsRestaurants = new HashMap<>();
        menuItems.forEach(m -> menuItemsRestaurants.put(m, getRestaurantDao().findRestaurantByMenuItem(m)));

        return menuItemsRestaurants;
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
