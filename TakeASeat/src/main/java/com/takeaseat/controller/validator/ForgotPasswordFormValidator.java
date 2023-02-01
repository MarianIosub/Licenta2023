package com.takeaseat.controller.validator;

import com.takeaseat.controller.form.ForgotPasswordForm;
import com.takeaseat.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.NoResultException;

import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_MAIL;
import static com.takeaseat.constants.MessagePropertiesConstants.NO_USER_FOUND_ERROR;
import static com.takeaseat.constants.StringConstants.MAIL;

public class ForgotPasswordFormValidator implements Validator {

    private final UserService userService;

    public ForgotPasswordFormValidator(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ForgotPasswordForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ForgotPasswordForm form = (ForgotPasswordForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, MAIL, NOT_EMPTY_MAIL);

        try {
            getUserService().findByMail(form.getMail());
        } catch (NoResultException e) {
            errors.rejectValue(MAIL, NO_USER_FOUND_ERROR);
        }
    }

    protected UserService getUserService() {
        return userService;
    }
}
