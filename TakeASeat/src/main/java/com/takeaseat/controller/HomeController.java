package com.takeaseat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.takeaseat.constants.EndpointsConstants.HOME_ENDPOINT;
import static com.takeaseat.constants.ViewsConstants.HOME_PAGE;

@Controller
public class HomeController {

    @RequestMapping(value = HOME_ENDPOINT, method = RequestMethod.GET)
    public String index() {
        return HOME_PAGE;
    }
}
