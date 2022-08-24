package com.takeaseat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.takeaseat.constants.EndpointsConstants.REGISTER_ENDPOINT;
import static com.takeaseat.constants.ViewsConstants.REGISTER_PAGE;

@Controller
public class UserController {

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.GET)
    public String getRegisterPage(){
        return REGISTER_PAGE;
    }
}
