package com.takeaseat.dao.impl;

import com.takeaseat.dao.RestaurantDao;
import com.takeaseat.model.Restaurant;
import com.takeaseat.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;

public class RestaurantDaoImpl implements RestaurantDao {

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public void save(Restaurant restaurant) {
        manager.persist(restaurant);
    }

    @Override
    public void findByUser(User user) {
        return;
    }
}
