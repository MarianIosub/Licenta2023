package com.takeaseat.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.takeaseat.constants.EndpointsConstants.LOGIN_ENDPOINT;
import static com.takeaseat.constants.StringConstants.LOGIN_ERROR;
import static com.takeaseat.constants.StringConstants.LOGIN_ERROR_MESSAGE;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        request.getSession().setAttribute(LOGIN_ERROR, LOGIN_ERROR_MESSAGE);
        response.sendRedirect(LOGIN_ENDPOINT);
    }
}
