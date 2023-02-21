package com.takeaseat.converter;


import com.stripe.model.Card;
import com.takeaseat.controller.dto.Cart;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Order;
import com.takeaseat.model.OrderEntry;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CartToOrderConverter {

	public Order convert(final Cart cart) {
		final Set<OrderEntry> orderEntries = getOrderEntriesFromCart(cart);
		return Order.builder()
					.user(cart.getUser())
					.restaurant(cart.getRestaurant())
					.cardExpirationMonth(((Card) cart.getCharge().getSource()).getExpMonth())
					.cardExpirationYear(((Card) cart.getCharge().getSource()).getExpYear())
					.cardLastDigits(((Card) cart.getCharge().getSource()).getLast4())
					.cardPaymentNetwork(((Card) cart.getCharge().getSource()).getBrand())
					.chargeId(cart.getCharge().getId())
					.startingHour(cart.getStartingHour())
					.endingHour(cart.getEndingHour())
					.noOfPeople(cart.getNoOfPeople())
					.date(cart.getDate())
					.creationDate(LocalDateTime.now())
					.totalPrice(cart.getTotalPrice())
					.transactionStatus(cart.getCharge().getStatus())
					.orderEntries(orderEntries)
					.build();
	}

	private Set<OrderEntry> getOrderEntriesFromCart(final Cart cart) {
		Set<OrderEntry> orderEntries = new HashSet<>();
		for (Map.Entry<MenuItem, Integer> entry : cart.getMenuItems().entrySet()) {
			orderEntries.add(OrderEntry.builder()
									   .name(entry.getKey().getName())
									   .price(entry.getKey().getPrice())
									   .photoLink(entry.getKey().getPhotoLink())
									   .quantity(entry.getValue())
									   .build());
		}
		return orderEntries;
	}
}
