package com.takeaseat.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator {

	private final String PASSWORD_PATTERN =
			"^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

	private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	public boolean isValid(final String password) {
		try {
			Matcher matcher = pattern.matcher(password);
			return matcher.matches();
		} catch (NullPointerException e) {
			return false;
		}
	}
}