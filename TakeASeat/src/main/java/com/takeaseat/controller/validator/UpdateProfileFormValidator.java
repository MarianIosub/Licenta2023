package com.takeaseat.controller.validator;

import com.takeaseat.controller.form.UpdateProfileForm;
import com.takeaseat.service.UserService;
import com.takeaseat.validator.PasswordValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.takeaseat.constants.MessagePropertiesConstants.INCORRECT_FORMAT_PASSWORD;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_NAME;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_PASSWORD;
import static com.takeaseat.constants.MessagePropertiesConstants.NOT_EMPTY_SURNAME;
import static com.takeaseat.constants.MessagePropertiesConstants.NO_MATCH_PASSWORD;
import static com.takeaseat.constants.MessagePropertiesConstants.UPDATE_PROFILE_MAIL_FORBIDDEN;
import static com.takeaseat.constants.StringConstants.MAIL;
import static com.takeaseat.constants.StringConstants.NAME;
import static com.takeaseat.constants.StringConstants.PASSWORD;
import static com.takeaseat.constants.StringConstants.SURNAME;


public class UpdateProfileFormValidator implements Validator {

	private final PasswordValidator passwordValidator;
	private final UserService userService;

	public UpdateProfileFormValidator(PasswordValidator passwordValidator, UserService userService) {
		this.passwordValidator = passwordValidator;
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateProfileForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UpdateProfileForm form = (UpdateProfileForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, NAME, NOT_EMPTY_NAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, SURNAME, NOT_EMPTY_SURNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, NOT_EMPTY_PASSWORD);

		if (!getUserService().getCurrentUser().getMail().equals(form.getMail())) {
			errors.rejectValue(MAIL, UPDATE_PROFILE_MAIL_FORBIDDEN);
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

	protected PasswordValidator getPasswordValidator() {
		return passwordValidator;
	}

	protected UserService getUserService() {
		return userService;
	}
}
