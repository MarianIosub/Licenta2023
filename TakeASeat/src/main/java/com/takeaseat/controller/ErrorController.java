package com.takeaseat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.takeaseat.constants.EndpointsConstants.ERROR_ENDPOINT;
import static com.takeaseat.constants.ViewsConstants.*;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
public class ErrorController {

    @RequestMapping(ERROR_ENDPOINT)
    public String handleError(@RequestAttribute(value = ERROR_STATUS_CODE, required = false) Integer status) {
        if (status == HttpStatus.NOT_FOUND.value()) {
            return ERROR_404;
        }
        if (status == HttpStatus.FORBIDDEN.value()) {
            return ERROR_403;
        }

        return ERROR_DEFAULT;
    }
}
