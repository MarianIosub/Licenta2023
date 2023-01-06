package com.takeaseat.dao.impl;

import com.takeaseat.dao.OrderDao;
import com.takeaseat.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;

public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public Order save(Order order) {
        manager.persist(order);
        manager.flush();
        return order;
    }
}
