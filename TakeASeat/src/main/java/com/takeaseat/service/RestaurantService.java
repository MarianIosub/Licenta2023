package com.takeaseat.service;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This interface defines the methods for a restaurant service.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface RestaurantService {

    /**
     * Saves a new restaurant with the information provided in the CreateRestaurantForm.
     *
     * @param createRestaurantForm the form containing the information for the new restaurant
     * @throws IOException if there is an error saving the restaurant
     */
    void saveRestaurant(final CreateRestaurantForm createRestaurantForm) throws IOException;

    /**
     * Checks if the current user has created a restaurant.
     *
     * @return true if the current user has created a restaurant, false otherwise
     */
    boolean hasCurrentUserRestaurantCreated();

    /**
     * Retrieves the restaurant created by the current user.
     *
     * @return the restaurant created by the current user
     */
    Restaurant getCurrentUserRestaurant();

    /**
     * Adds a menu item to the specified restaurant.
     *
     * @param restaurant the restaurant to add the menu item to
     * @param menuItem the menu item to add to the restaurant
     * @return the updated set of menu items for the restaurant
     */
    Set<MenuItem> addMenuItemToRestaurant(Restaurant restaurant, MenuItem menuItem);

    /**
     * Searches for menu items matching the specified search term.
     *
     * @param searchedItem the search term to match menu items against
     * @return the set of menu items matching the search term
     */
    Set<MenuItem> searchForMenuItems(final String searchedItem);

    /**
     * Changes the availability of a menu item by its id.
     *
     * @param menuItemId the id of the menu item to change the availability of
     */
    void changeMenuItemAvailability(Long menuItemId);

    /**
     * Deletes a menu item by its id.
     *
     * @param menuItemId the id of the menu item to delete
     */
    void deleteMenuItem(Long menuItemId);

    /**
     * Retrieves all restaurants.
     *
     * @return the list of all restaurants
     */
    List<Restaurant> getAllRestaurants();

    /**
     * Searches for restaurants matching the specified search term and sort option.
     *
     * @param searchedItem the search term to match restaurants against
     * @param sortOption the option to sort the results by
     * @return the list of restaurants matching the search term and sort option
     */
    List<Restaurant> searchForRestaurant(String searchedItem, String sortOption);

    /**
     * Retrieves a restaurant by its id.
     *
     * @param restaurantId the id of the restaurant to retrieve
     * @return the restaurant with the specified id
     */
    Restaurant getRestaurantById(String restaurantId);

    /**
     * Retrieves the menu items for a restaurant by its id, searched item and sort option.
     *
     * @param restaurantId the id of the restaurant to retrieve menu items for
     * @param searchedItem the search term to match menu items against
     * @param sortOption the option to sort the results by
     * @return the set of menu items for the specified restaurant, matching the search term and sort option
     */
    Set<MenuItem> getMenuItemsForRestaurant(String restaurantId, String searchedItem, String sortOption);

    /**
     * Checks if the specified set of menu items contains any available items.
     *
     * @param menuItems the set of menu items to check for availability
     * @return true if the set contains any available items, false otherwise
     */
    boolean hasAvailableItems(Set<MenuItem> menuItems);

    /**
     * Checks if the specified set of menu items contains any unavailable items.
     *
     * @param menuItems the set of menu items to check for availability
     * @return true if the set contains any unavailable items, false otherwise
     */
    boolean hasUnavailableItems(Set<MenuItem> menuItems);

    /**
     * Retrieves the most rated restaurants.
     *
     * @return the list of the most rated restaurants
     */
    List<Restaurant> getMostRatedRestaurants();

    /**
     * Retrieves the most ordered restaurants.
     *
     * @return the list of the most ordered restaurants
     */
    List<Restaurant> getMostOrderedRestaurants();

    /**
     * Retrieves the most ordered menu items.
     *
     * @return the map of the most ordered menu items and their corresponding restaurants
     */
    Map<MenuItem, Restaurant> getMostOrderedMenuItems();
}
