package com.takeaseat.controller;

import com.takeaseat.security.MyUserPrincipal;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static com.takeaseat.constants.EndpointsConstants.HOME_ENDPOINT;
import static com.takeaseat.constants.StringConstants.CURRENT_USER;
import static com.takeaseat.constants.StringConstants.HOME_MENU_ITEMS;
import static com.takeaseat.constants.StringConstants.HOME_ORDERED_RESTAURANTS;
import static com.takeaseat.constants.StringConstants.HOME_RATED_RESTAURANTS;
import static com.takeaseat.constants.StringConstants.LOGIN_ERROR;
import static com.takeaseat.constants.ViewsConstants.HOME_PAGE;

@Controller
@AllArgsConstructor
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    private final RestaurantService restaurantService;
    private final UserService userService;


    @RequestMapping(value = HOME_ENDPOINT, method = RequestMethod.GET)
    public String index(HttpSession session, MyUserPrincipal principal, Authentication authentication, Model model) {
        session.removeAttribute(LOGIN_ERROR);
        model.addAttribute(CURRENT_USER, getUserService().getCurrentUser());
        model.addAttribute(HOME_RATED_RESTAURANTS, getRestaurantService().getMostRatedRestaurants());
        model.addAttribute(HOME_ORDERED_RESTAURANTS, getRestaurantService().getMostOrderedRestaurants());
        model.addAttribute(HOME_MENU_ITEMS, getRestaurantService().getMostOrderedMenuItems());
        return HOME_PAGE;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public UserService getUserService() {
        return userService;
    }
}
