package com.takeaseat.dao.impl;

import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;
import static java.lang.String.format;

public class RestaurantDaoImpl implements RestaurantDao {

    private static final String RESTAURANT_BY_USER = "SELECT a FROM Restaurant a WHERE a.administrator.id='%s'";
    private static final String FIND_ALL_RESTAURANTS = "SELECT a FROM Restaurant a";
    private static final String MOST_RATED_RESTAURANT = "SELECT a FROM Restaurant a ORDER BY a.rating DESC";
    private static final String MOST_ORDERED_RESTAURANT = "SELECT a FROM Restaurant a ORDER BY a.noOfReservations DESC";
    private static final String RESTAURANT_BY_MENU_ITEM = "SELECT a from Restaurant a where '%s' in (SELECT m.id from a.menuItems m)";
    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public void save(Restaurant restaurant) {
        getManager().persist(restaurant);
    }

    @Override
    public Restaurant findByUser(User user) {
        return (Restaurant) getManager().createQuery(format(RESTAURANT_BY_USER, user.getId())).getSingleResult();
    }

    @Override
    public void update(Restaurant restaurant) {
        getManager().merge(restaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        return getManager().createQuery(FIND_ALL_RESTAURANTS, Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findById(long id) {
        return getManager().find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getRestaurantsByHighestRating() {
        List<Restaurant> restaurants = getManager().createQuery(MOST_RATED_RESTAURANT, Restaurant.class).getResultList();
        return restaurants.subList(0, Math.min(restaurants.size(), 3));
    }

    @Override
    public List<Restaurant> getRestaurantsByNoOfOrders() {
        List<Restaurant> restaurants = getManager().createQuery(MOST_ORDERED_RESTAURANT, Restaurant.class).getResultList();
        return restaurants.subList(0, Math.min(restaurants.size(), 3));
    }

    @Override
    public Restaurant findRestaurantByMenuItem(MenuItem menuItem) {
        return getManager().createQuery(format(RESTAURANT_BY_MENU_ITEM, menuItem.getId()), Restaurant.class).getSingleResult();
    }

    protected EntityManager getManager() {
        return manager;
    }
}
