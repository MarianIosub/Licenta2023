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
import static com.takeaseat.constants.MessagePropertiesConstants.ALREADY_USED_REGISTER_MAIL;
import static com.takeaseat.constants.MessagePropertiesConstants.INCORRECT_FORMAT_MAIL;
import static com.takeaseat.constants.MessagePropertiesConstants.INCORRECT_FORMAT_PASSWORD;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_CONFIRM_PASSWORD;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_MAIL;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_NAME;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_PASSWORD;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_ROLE;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_SURNAME;
import static com.takeaseat.constants.MessagePropertiesConstants.NO_MATCH_PASSWORD;
import static com.takeaseat.constants.StringConstants.CONFIRM_PASSWORD;
import static com.takeaseat.constants.StringConstants.MAIL;
import static com.takeaseat.constants.StringConstants.NAME;
import static com.takeaseat.constants.StringConstants.PASSWORD;
import static com.takeaseat.constants.StringConstants.ROLE;
import static com.takeaseat.constants.StringConstants.SURNAME;


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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME, NOT_EMPTY_NAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, SURNAME, NOT_EMPTY_SURNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MAIL, NOT_EMPTY_MAIL);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, NOT_EMPTY_PASSWORD);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CONFIRM_PASSWORD, NOT_EMPTY_CONFIRM_PASSWORD);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ROLE, NOT_EMPTY_ROLE);

		try {
			getUserService().findByMail(form.getMail());
			errors.rejectValue(MAIL, ALREADY_USED_REGISTER_MAIL);
			return;
		} catch (NoResultException e) {
			LOG.info(NO_USER_FOUND);
		}

		if (!getEmailValidator().isValid(form.getMail())) {
			errors.rejectValue(MAIL, INCORRECT_FORMAT_MAIL);
			return;
		}

		if (!getPasswordValidator().isValid(form.getPassword())) {
			errors.rejectValue(PASSWORD, INCORRECT_FORMAT_PASSWORD);
			return;
		}

		if (!form.getPassword().equals(form.getConfirmPassword())) {
			errors.rejectValue(PASSWORD, NO_MATCH_PASSWORD);
		}
	}

	protected UserService getUserService() {
		return userService;
	}

	protected EmailValidator getEmailValidator() {
		return emailValidator;
	}

	protected PasswordValidator getPasswordValidator() {
		return passwordValidator;
	}
}
