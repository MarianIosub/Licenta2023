package com.takeaseat.controller.validator;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.service.UserService;
import com.takeaseat.validator.EmailValidator;
import com.takeaseat.validator.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.NoResultException;

import static com.takeaseat.constants.ExceptionMessages.NO_USER_FOUND;
import static com.takeaseat.constants.StringConstants.*;
import static com.takeaseat.constants.ValidatorConstants.*;

public class RegisterFormValidator implements Validator {

    private final Logger LOG = LoggerFactory.getLogger(RegisterFormValidator.class);

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    public RegisterFormValidator(UserService userService, EmailValidator emailValidator, PasswordValidator passwordValidator) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterForm form = (RegisterForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME, NOT_EMPTY_REGISTER_NAME);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, SURNAME, NOT_EMPTY_REGISTER_SURNAME);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, MAIL, NOT_EMPTY_REGISTER_MAIL);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, NOT_EMPTY_REGISTER_PASSWORD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, CONFIRM_PASSWORD, NOT_EMPTY_REGISTER_CONFIRM_PASSWORD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ROLE, NOT_EMPTY_REGISTER_ROLE);

        try {
            userService.findByMail(form.getMail());
            errors.rejectValue(MAIL, ALREADY_USED_REGISTER_MAIL);
            return;
        } catch (NoResultException e) {
            LOG.info(NO_USER_FOUND);
        }

        if (!emailValidator.isValid(form.getMail())) {
            errors.rejectValue(MAIL, INCORRECT_FORMAT_REGISTER_MAIL);
            return;
        }

        if (!passwordValidator.isValid(form.getPassword())) {
            errors.rejectValue(PASSWORD, INCORRECT_FORMAT_REGISTER_PASSWORD);
            return;
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue(PASSWORD, NO_MATCH_REGISTER_PASSWORD);
        }
    }
}