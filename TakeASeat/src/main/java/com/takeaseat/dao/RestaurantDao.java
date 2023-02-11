package com.takeaseat.dao;

import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import java.util.List;


/**
 * This interface defines the methods for a Restaurant Data Access Object.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface RestaurantDao {

	/**
	 * Save a restaurant.
	 *
	 * @param restaurant the restaurant to save
	 */
	void save(Restaurant restaurant);

	/**
	 * Find a restaurant by its user.
	 *
	 * @param user the user to find the restaurant for
	 *
	 * @return the restaurant for the specified user
	 */
	Restaurant findByUser(User user);

	/**
	 * Update a restaurant.
	 *
	 * @param restaurant the restaurant to update
	 */
	void update(Restaurant restaurant);

	/**
	 * Find all restaurants.
	 *
	 * @return the list of all restaurants
	 */
	List<Restaurant> findAll();

	/**
	 * Find a restaurant by its id.
	 *
	 * @param id the id of the restaurant to find
	 *
	 * @return the restaurant with the specified id
	 */
	Restaurant findById(long id);

	/**
	 * Get all restaurants by highest rating.
	 *
	 * @return the list of restaurants sorted by highest rating
	 */
	List<Restaurant> getRestaurantsByHighestRating();

	/**
	 * Get all restaurants by number of orders.
	 *
	 * @return the list of restaurants sorted by number of orders
	 */
	List<Restaurant> getRestaurantsByNoOfOrders();

	/**
	 * Finds a restaurant by its menu item.
	 *
	 * @param menuItem the menu item to find the restaurant for
	 *
	 * @return the restaurant for the specified menu item
	 */
	Restaurant findRestaurantByMenuItem(MenuItem menuItem);
}
