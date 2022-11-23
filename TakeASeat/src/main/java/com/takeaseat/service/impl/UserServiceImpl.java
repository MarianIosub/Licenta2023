package com.takeaseat.service.impl;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.form.UpdateProfileForm;
import com.takeaseat.converter.Converter;
import com.takeaseat.dao.UserDao;
import com.takeaseat.model.User;
import com.takeaseat.security.MyUserPrincipal;
import com.takeaseat.service.EmailService;
import com.takeaseat.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final Converter<RegisterForm, User> registerFormUserConverter;
    private final Converter<User, UpdateProfileForm> userUpdateProfileFormConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;


    public UserServiceImpl(final UserDao userDao, final Converter<RegisterForm, User> registerFormUserConverter,
                           final Converter<User, UpdateProfileForm> userUpdateProfileFormConverter,
                           final BCryptPasswordEncoder bCryptPasswordEncoder, final EmailService emailService) {
        this.userDao = userDao;
        this.registerFormUserConverter = registerFormUserConverter;
        this.userUpdateProfileFormConverter = userUpdateProfileFormConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }


    @Override
    public User findByMail(final String mail) {
        return getUserDao().findByMail(mail);
    }

    @Override
    public void registerUser(final RegisterForm form) {
        User user = getRegisterFormUserConverter().convert(form, User.class);
        encryptUserPassword(user);

        getEmailService().sendWelcomeEmail(user);

        getUserDao().save(user);
    }

    @Override
    public UpdateProfileForm getUpdateProfileForm() {
        User user = getUserDao().findByMail(getCurrentUser().getMail());
        UpdateProfileForm updateProfileForm = getUserUpdateProfileFormConverter().convert(user, UpdateProfileForm.class);
        updateProfileForm.setPassword(null);
        return updateProfileForm;
    }

    @Override
    public User getCurrentUser() {
        return ((MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    @Override
    public void updateCurrentUser(UpdateProfileForm updateProfileForm) {
        User user = getCurrentUser();
        user.setSurname(updateProfileForm.getSurname());
        user.setName(updateProfileForm.getName());
        user.setPassword(encryptPassword(updateProfileForm.getPassword()));
        getUserDao().update(user);
    }

    @Override
    public void updateLastLoginDate() {
        User user = getCurrentUser();
        user.setLastLoginDate(LocalDateTime.now());
        userDao.update(user);
    }

    @Override
    public MyUserPrincipal loadUserByUsername(final String mail) throws UsernameNotFoundException {
        User user = getUserDao().findByMail(mail);
        return new MyUserPrincipal(user);
    }

    private void encryptUserPassword(User user) {
        String password = user.getPassword();
        password = encryptPassword(password);
        user.setPassword(password);
    }

    private String encryptPassword(final String password) {
        return getbCryptPasswordEncoder().encode(password);
    }

    protected UserDao getUserDao() {
        return userDao;
    }

    protected Converter<RegisterForm, User> getRegisterFormUserConverter() {
        return registerFormUserConverter;
    }

    protected Converter<User, UpdateProfileForm> getUserUpdateProfileFormConverter() {
        return userUpdateProfileFormConverter;
    }

    protected BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    protected EmailService getEmailService() {
        return emailService;
    }
}
