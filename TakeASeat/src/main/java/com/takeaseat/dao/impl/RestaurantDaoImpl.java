package com.takeaseat.dao.impl;

import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;
import static java.lang.String.format;

public class RestaurantDaoImpl implements RestaurantDao {

    String RESTAURANT_BY_USER = "SELECT a FROM Restaurant a WHERE a.user.id='%s'";

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

    protected EntityManager getManager() {
        return manager;
    }
}
