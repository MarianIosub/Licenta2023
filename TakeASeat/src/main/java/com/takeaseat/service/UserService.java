package com.takeaseat.service;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findByMail(String mail);

    void registerUser(RegisterForm form);
}
