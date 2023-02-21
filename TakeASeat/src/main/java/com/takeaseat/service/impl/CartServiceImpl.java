package com.takeaseat.service.impl;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.model.MenuItem;
import com.takeaseat.service.CartService;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Transactional
public class CartServiceImpl implements CartService {
	private final UserService userService;
	private final RestaurantService restaurantService;

	public CartServiceImpl(final UserService userService, final RestaurantService restaurantService) {
		this.userService = userService;
		this.restaurantService = restaurantService;
	}

	@Override
	public Cart addToCart(Cart cart, Long menuItemId) {
		MenuItem menuItem = getMenuItemFromRestaurant(cart, menuItemId);
		if (cart.getMenuItems().containsKey(menuItem)) {
			cart.getMenuItems().put(menuItem, cart.getMenuItems().get(menuItem) + 1);
		} else {
			cart.getMenuItems().put(menuItem, 1);
		}
		calculateCartTotal(cart);

		return cart;
	}

	@Override
	public Cart calculateCartTotal(Cart cart) {
		AtomicReference<Double> total = new AtomicReference<>(cart.getRestaurant().getPriceRequired());
		cart.getMenuItems().forEach((k, v) -> total.updateAndGet(v1 -> v1 + k.getPrice() * v));
		cart.setTotalPrice(total.get());

		return cart;
	}

	@Override
	public Cart deleteFromCart(Cart cart, Long menuItemId) {
		cart.getMenuItems().remove(getMenuItemFromRestaurant(cart, menuItemId));

		calculateCartTotal(cart);
		return cart;
	}

	@Override
	public void increaseQuantityForMenuItem(Cart cart, Long menuItemId) {
		MenuItem menuItem = getMenuItemFromRestaurant(cart, menuItemId);
		cart.getMenuItems().put(menuItem, cart.getMenuItems().get(menuItem) + 1);
		calculateCartTotal(cart);
	}

	@Override
	public void decreaseQuantityForMenuItem(Cart cart, Long menuItemId) {
		MenuItem menuItem = getMenuItemFromRestaurant(cart, menuItemId);
		if (cart.getMenuItems().get(menuItem).equals(1)) {
			deleteFromCart(cart, menuItemId);
		} else {
			cart.getMenuItems().put(menuItem, cart.getMenuItems().get(menuItem) - 1);
		}
	}

	@Override
	public void setCartDate(Cart cart, String date) {
		cart.setDate(LocalDate.parse(date));
		checkIfOrderCanBePlaced(cart);
	}

	@Override
	public void setCartReservationStart(Cart cart, String start) {
		Double startHour = Double.parseDouble(start);
		if (cart.getRestaurant().getOpeningHour() > startHour) {
			startHour = cart.getRestaurant().getOpeningHour();
		}
		if (nonNull(cart.getEndingHour()) && cart.getEndingHour() < (startHour + 0.2)) {
			startHour = cart.getEndingHour() - 1.0;
		}
		if (isNull(cart.getEndingHour()) && cart.getRestaurant().getClosingHour() < (startHour + 1.0)) {
			startHour = cart.getRestaurant().getClosingHour() - 1.0;
		}
		cart.setStartingHour(startHour);
		checkIfOrderCanBePlaced(cart);
	}

	@Override
	public void setCartReservationEnd(Cart cart, String end) {
		Double endHour = Double.parseDouble(end);
		if (cart.getRestaurant().getClosingHour() < endHour) {
			endHour = cart.getRestaurant().getClosingHour();
		}
		if (nonNull(cart.getStartingHour()) && cart.getStartingHour() > (endHour - 0.2)) {
			endHour = cart.getStartingHour() + 1.0;
		}
		if (isNull(cart.getStartingHour()) && cart.getRestaurant().getOpeningHour() > (endHour - 1.0)) {
			endHour = cart.getRestaurant().getOpeningHour() + 1.0;
		}
		cart.setEndingHour(endHour);
		checkIfOrderCanBePlaced(cart);
	}

	@Override
	public void setNoOfPeople(Cart cart, Integer noOfPeople) {
		cart.setNoOfPeople(noOfPeople);
		checkIfOrderCanBePlaced(cart);
	}

	private MenuItem getMenuItemFromRestaurant(Cart cart, Long menuItemId) {
		return cart.getRestaurant().getMenuItems().stream()
				   .filter(m -> m.getId().equals(menuItemId))
				   .findFirst()
				   .orElse(null);
	}

	private void checkIfOrderCanBePlaced(Cart cart) {
		cart.setCanBePlaced(
				nonNull(cart.getDate()) && nonNull(cart.getEndingHour()) && nonNull(cart.getStartingHour()) && nonNull(
						cart.getNoOfPeople()));
	}

	protected UserService getUserService() {
		return userService;
	}

	protected RestaurantService getRestaurantService() {
		return restaurantService;
	}

}
