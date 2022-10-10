package com.takeaseat.controller;

import com.takeaseat.security.MyUserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static com.takeaseat.constants.EndpointsConstants.HOME_ENDPOINT;
import static com.takeaseat.constants.StringConstants.LOGIN_ERROR;
import static com.takeaseat.constants.ViewsConstants.HOME_PAGE;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = HOME_ENDPOINT, method = RequestMethod.GET)
    public String index(HttpSession session, MyUserPrincipal principal, Authentication authentication) {
        session.removeAttribute(LOGIN_ERROR);
        if (authentication != null) {
            LOG.info(principal.toString());
            LOG.info(authentication.getPrincipal().toString());
            LOG.info(((MyUserPrincipal) authentication.getPrincipal()).toString());
        }
        return HOME_PAGE;
    }
}
