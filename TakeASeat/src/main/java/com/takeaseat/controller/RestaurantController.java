package com.takeaseat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.takeaseat.constants.EndpointsConstants.CREATE_RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.ViewsConstants.CREATE_RESTAURANT_PAGE;

@Controller
@RequestMapping(RESTAURANT_ENDPOINT)
public class RestaurantController {

    @RequestMapping(value = CREATE_RESTAURANT_ENDPOINT, method = RequestMethod.GET)
    public String getNewRestaurantForm(Model model) {
        return CREATE_RESTAURANT_PAGE;
    }

}
