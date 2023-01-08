package com.takeaseat.dao.impl;

import com.takeaseat.dao.MenuItemDao;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;

public class MenuItemDaoImpl implements MenuItemDao {

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public List<MenuItem> getMenuItemsByRestaurant(Restaurant restaurant) {
        return new ArrayList<>(getManager().find(Restaurant.class, restaurant.getId()).getMenuItems());
    }

    protected EntityManager getManager() {
        return manager;
    }
}
