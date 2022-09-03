package com.takeaseat.dao;

import com.takeaseat.model.User;

public interface UserDao {
    User findByMail(String mail);

    void save(User user);
}
