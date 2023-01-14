package com.takeaseat.dao.impl;

import com.takeaseat.dao.MenuItemDao;
import com.takeaseat.model.MenuItem;
import com.takeaseat.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;

public class MenuItemDaoImpl implements MenuItemDao {

    private final static String MOST_ORDERED_ITEMS = "SELECT a from MenuItem a ORDER BY a.noOfOrders DESC";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public Set<MenuItem> getMenuItemsByRestaurant(Restaurant restaurant) {
        return getManager().find(Restaurant.class, restaurant.getId()).getMenuItems();
    }

    @Override
    public Set<MenuItem> getMostOrderedMenuItems() {
        Set<MenuItem> menuItems = getManager().createQuery(MOST_ORDERED_ITEMS, MenuItem.class).getResultStream().collect(Collectors.toSet());

        return menuItems.stream()
                .sorted(Comparator.comparing(MenuItem::getNoOfOrders).reversed())
                .skip(0)
                .limit(Math.min(menuItems.size(), 3))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(MenuItem menuItem) {
        getManager().remove(menuItem);
    }

    protected EntityManager getManager() {
        return manager;
    }
}
