package com.takeaseat.dao.impl;

import com.takeaseat.dao.OrderDao;
import com.takeaseat.model.Order;
import com.takeaseat.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;
import static com.takeaseat.constants.StringConstants.ROLE_FOOD_LOVER;
import static java.lang.String.format;


public class OrderDaoImpl implements OrderDao {

	private final static String ORDERS_BY_USER_QUERY = "SELECT a from Order a WHERE a.user.id = '%s'";
	private final static String ORDERS_BY_RESTAURANT_QUERY = "SELECT a from Order a WHERE a.restaurant.administrator.id = '%s'";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager manager;

	@Override
	public Order save(final Order order) {
		getManager().persist(order);
		getManager().flush();
		return order;
	}

	@Override
	public Order findById(final Long orderId) {
		return getManager().find(Order.class, orderId);
	}

	@Override
	public List<Order> getOrdersByUser(final User currentUser) {
		if (currentUser.getRole().equals(ROLE_FOOD_LOVER)) {
			return getManager().createQuery(format(ORDERS_BY_USER_QUERY, currentUser.getId()), Order.class).getResultList();
		}
		return getManager().createQuery(format(ORDERS_BY_RESTAURANT_QUERY, currentUser.getId()), Order.class).getResultList();
	}

	@Override
	public void updateOrder(Order order) {
		getManager().merge(order);
	}

	protected EntityManager getManager() {
		return manager;
	}
}
