package com.takeaseat.dao.impl;

import com.takeaseat.dao.UserDao;
import com.takeaseat.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.takeaseat.constants.StringConstants.PERSISTENCE_UNIT_NAME;
import static java.lang.String.format;

public class UserDaoImpl implements UserDao {

    String USER_BY_MAIL = "SELECT a FROM User a WHERE a.mail='%s'";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager manager;

    @Override
    public User findByMail(final String mail) {
        return (User) getManager().createQuery(format(USER_BY_MAIL, mail)).getSingleResult();
    }

    @Override
    public void save(final User user) {
        getManager().persist(user);
    }

    @Override
    public void update(User user) {
        getManager().merge(user);
    }

    protected EntityManager getManager() {
        return manager;
    }
}
