package com.takeaseat.dao;

import com.takeaseat.model.User;

/**
 * This interface defines the methods for a User Data Access Object.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface UserDao {

    /**
     * Finds a user by their email address.
     *
     * @param mail the email address of the user to find
     * @return the user with the specified email address
     */
    User findByMail(String mail);

    /**
     * Save a user.
     *
     * @param user the user to save
     */
    void save(User user);

    /**
     * Update a user.
     *
     * @param user the user to update
     */
    void update(User user);
}
