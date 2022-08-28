package com.takeaseat.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private final String EMAIL_PATTERN =
            "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}