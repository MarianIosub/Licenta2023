package com.takeaseat.security;

import com.takeaseat.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.takeaseat.constants.EndpointsConstants.HOME_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.MANAGE_RESTAURANT_ENPOINT;
import static com.takeaseat.constants.EndpointsConstants.RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.StringConstants.ROLE_ADMINISTRATOR;
import static com.takeaseat.helper.CreateEndpointHelper.createEndpoint;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public LoginSuccessHandler(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        updateLastLoginDate();

        if (isAdministrator(authentication)) {
            response.sendRedirect(createEndpoint(RESTAURANT_ENDPOINT, MANAGE_RESTAURANT_ENPOINT));
        } else {
            response.sendRedirect(HOME_ENDPOINT);
        }
    }

    private void updateLastLoginDate() {
        userService.updateLastLoginDate();
    }

    private boolean isAdministrator(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ROLE_ADMINISTRATOR));
    }
}
