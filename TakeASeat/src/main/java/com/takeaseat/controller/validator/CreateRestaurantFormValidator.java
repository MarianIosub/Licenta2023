package com.takeaseat.controller.validator;

import com.takeaseat.controller.form.CreateRestaurantForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CreateRestaurantFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateRestaurantForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
