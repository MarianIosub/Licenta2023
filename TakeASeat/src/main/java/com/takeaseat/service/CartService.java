package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;


/**
 * This interface defines the methods for a cart service.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface CartService {

	/**
	 * Adds a menu item to the cart.
	 *
	 * @param cart the cart to add the menu item to
	 * @param menuItemId the id of the menu item to add
	 *
	 * @return the updated cart
	 */
	Cart addToCart(Cart cart, Long menuItemId);

	/**
	 * Calculates the total cost of the items in the cart.
	 *
	 * @param cart the cart to calculate the total for
	 *
	 * @return the updated cart with the total cost
	 */
	Cart calculateCartTotal(Cart cart);

	/**
	 * Deletes a menu item from the cart.
	 *
	 * @param cart the cart to delete the menu item from
	 * @param menuItemId the id of the menu item to delete
	 *
	 * @return the updated cart
	 */
	Cart deleteFromCart(Cart cart, Long menuItemId);

	/**
	 * Increases the quantity of a menu item in the cart.
	 *
	 * @param cart the cart to update
	 * @param menuItemId the id of the menu item to increase the quantity of
	 */
	void increaseQuantityForMenuItem(Cart cart, Long menuItemId);

	/**
	 * Decreases the quantity of a menu item in the cart.
	 *
	 * @param cart the cart to update
	 * @param menuItemId the id of the menu item to decrease the quantity of
	 */
	void decreaseQuantityForMenuItem(Cart cart, Long menuItemId);

	/**
	 * Sets the date of the cart.
	 *
	 * @param cart the cart to update
	 * @param date the date to set for the cart
	 */
	void setCartDate(Cart cart, String date);

	/**
	 * Sets the start time of the reservation for the cart.
	 *
	 * @param cart the cart to update
	 * @param start the start time to set for the reservation
	 */
	void setCartReservationStart(Cart cart, String start);

	/**
	 * Sets the end time of the reservation for the cart.
	 *
	 * @param cart the cart to update
	 * @param end the end time to set for the reservation
	 */
	void setCartReservationEnd(Cart cart, String end);

	void setNoOfPeople(Cart cart, Integer noOfPeople);
}
