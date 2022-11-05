package com.takeaseat.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.takeaseat.constants.EndpointsConstants.*;
import static com.takeaseat.constants.StringConstants.ROLE_FOOD_LOVER;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ROLE_FOOD_LOVER))) {
            response.sendRedirect(HOME_ENDPOINT);
        } else {
            response.sendRedirect(RESTAURANT_ENDPOINT + MANAGE_RESTAURANT_ENPOINT);
        }
    }
}
