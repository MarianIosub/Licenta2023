package com.takeaseat.service.impl;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.converter.Converter;
import com.takeaseat.dao.UserDao;
import com.takeaseat.model.User;
import com.takeaseat.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Converter<RegisterForm, User> registerFormUserConverter;


    public UserServiceImpl(final UserDao userDao, Converter<RegisterForm, User> registerFormUserConverter) {
        this.userDao = userDao;
        this.registerFormUserConverter = registerFormUserConverter;
    }


    @Override
    public User findByMail(String mail) {
        return userDao.findByMail(mail);
    }

    @Override
    public void registerUser(RegisterForm form) {
        User user = registerFormUserConverter.convert(form, User.class);
        encryptUserPassword(user);


        userDao.save(user);
    }

    private void encryptUserPassword(User user) {
        String password = user.getPassword();
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(password);
    }
}
