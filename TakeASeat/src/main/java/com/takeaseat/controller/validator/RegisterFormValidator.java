package com.takeaseat.controller.validator;

import com.takeaseat.controller.form.RegisterForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterForm form = (RegisterForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.registerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty.registerForm.surname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "NotEmpty.registerForm.mail");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.registerForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.registerForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role","NotEmpty.registerForm.role");
    }
}
