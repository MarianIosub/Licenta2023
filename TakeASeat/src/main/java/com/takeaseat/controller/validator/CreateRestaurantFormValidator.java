package com.takeaseat.controller.validator;

import com.takeaseat.controller.form.CreateRestaurantForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.takeaseat.constants.MessagePropertiesConstants.*;
import static com.takeaseat.constants.StringConstants.*;

public class CreateRestaurantFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateRestaurantForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateRestaurantForm form = (CreateRestaurantForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME, NOT_EMPTY_NAME);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PHONE_NUMBER, NOT_EMPTY_SURNAME);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, MAIL, NOT_EMPTY_MAIL);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, DESCRIPTION, NOT_EMPTY_PASSWORD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ADDRESS, NOT_EMPTY_CONFIRM_PASSWORD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PRICE_REQUIRED, NOT_EMPTY_ROLE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, CITY, NOT_EMPTY_CITY);
    }
}
