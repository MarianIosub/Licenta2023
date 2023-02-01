package com.takeaseat.service;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.form.UpdateProfileForm;
import com.takeaseat.model.User;

/**
 * This interface defines the methods for a user service.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface UserService {

    /**
     * Finds a user by their email address.
     *
     * @param mail the email address of the user to find
     * @return the user with the specified email address
     */
    User findByMail(final String mail);

    /**
     * Registers a new user with the information provided in the RegisterForm.
     *
     * @param form the form containing the information for the new user
     */
    void registerUser(final RegisterForm form);

    /**
     * Retrieves the UpdateProfileForm for the current user.
     *
     * @return the UpdateProfileForm for the current user
     */
    UpdateProfileForm getUpdateProfileForm();

    /**
     * Retrieves the current user.
     *
     * @return the current user
     */
    User getCurrentUser();

    /**
     * Updates the current user with the information provided in the UpdateProfileForm.
     *
     * @param updateProfileForm the form containing the updated information for the current user
     */
    void updateCurrentUser(UpdateProfileForm updateProfileForm);

    /**
     * Updates the last login date of the current user.
     */
    void updateLastLoginDate();

    /**
     * Sends an email to user with new password and changes his password with sent one.
     *
     * @param mail user's mail for recovering password
     */
    void recoverPassword(final String mail);
}
