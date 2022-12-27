package com.takeaseat.dao.impl;

import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;
import static java.lang.String.format;

public class RestaurantDaoImpl implements RestaurantDao {

    String RESTAURANT_BY_USER = "SELECT a FROM Restaurant a WHERE a.administrator.id='%s'";
    String FIND_ALL_RESTAURANTS = "SELECT a FROM Restaurant a";

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

    protected EntityManager getManager() {
        return manager;
    }
}
