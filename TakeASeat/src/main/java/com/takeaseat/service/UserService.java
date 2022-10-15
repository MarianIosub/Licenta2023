package com.takeaseat.service;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.form.UpdateProfileForm;
import com.takeaseat.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findByMail(final String mail);

    void registerUser(final RegisterForm form);

    UpdateProfileForm getUpdateProfileForm();

    User getCurrentUser();

    void updateCurrentUser(UpdateProfileForm updateProfileForm);
}
