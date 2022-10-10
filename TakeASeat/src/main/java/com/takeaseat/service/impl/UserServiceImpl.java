package com.takeaseat.service.impl;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.converter.Converter;
import com.takeaseat.dao.UserDao;
import com.takeaseat.model.User;
import com.takeaseat.security.MyUserPrincipal;
import com.takeaseat.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final Converter<RegisterForm, User> registerFormUserConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(final UserDao userDao, Converter<RegisterForm, User> registerFormUserConverter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.registerFormUserConverter = registerFormUserConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        password = bCryptPasswordEncoder.encode(password);
        user.setPassword(password);
    }

    @Override
    public MyUserPrincipal loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userDao.findByMail(mail);
        return new MyUserPrincipal(user);
    }
}
