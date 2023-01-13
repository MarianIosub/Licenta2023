package com.takeaseat.dao.impl;

import com.takeaseat.dao.MenuItemDao;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;

public class MenuItemDaoImpl implements MenuItemDao {

    private final static String MOST_ORDERED_ITEMS = "SELECT a from MenuItem a ORDER BY a.noOfOrders DESC";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public List<MenuItem> getMenuItemsByRestaurant(Restaurant restaurant) {
        return new ArrayList<>(getManager().find(Restaurant.class, restaurant.getId()).getMenuItems());
    }

    @Override
    public List<MenuItem> getMostOrderedMenuItems() {
        List<MenuItem> menuItems = getManager().createQuery(MOST_ORDERED_ITEMS, MenuItem.class).getResultList();

        return menuItems.subList(0, Math.min(menuItems.size(), 3));
    }

    @Override
    public void delete(MenuItem menuItem) {
        getManager().remove(menuItem);
    }

    protected EntityManager getManager() {
        return manager;
    }
}
