package com.takeaseat.controller;

import com.takeaseat.controller.form.CreateRestaurantForm;
import com.takeaseat.controller.validator.CreateRestaurantFormValidator;
import com.takeaseat.service.RestaurantService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.takeaseat.constants.EndpointsConstants.CREATE_RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.RESTAURANT_ENDPOINT;
import static com.takeaseat.constants.StringConstants.CREATE_RESTAURANT_FORM;
import static com.takeaseat.constants.ViewsConstants.CREATE_RESTAURANT_PAGE;
import static com.takeaseat.constants.ViewsConstants.HOME_PAGE;
import static java.util.Objects.isNull;

@Controller
@RequestMapping(RESTAURANT_ENDPOINT)
@AllArgsConstructor
public class RestaurantController {

    private final UserService userService;
    private final CreateRestaurantFormValidator createRestaurantFormValidator;
    private final RestaurantService restaurantService;

    @InitBinder(CREATE_RESTAURANT_FORM)
    protected void initBinderRegister(WebDataBinder binder) {
        binder.setValidator(getCreateRestaurantFormValidator());
    }


    @RequestMapping(value = CREATE_RESTAURANT_ENDPOINT, method = RequestMethod.GET)
    public String getNewRestaurantForm(@ModelAttribute(CREATE_RESTAURANT_FORM) CreateRestaurantForm createRestaurantForm,
                                       Model model) {
        if (isNull(createRestaurantForm.getEmail())) {
            createRestaurantForm.setEmail(getUserService().getCurrentUser().getMail());
        }
        return CREATE_RESTAURANT_PAGE;
    }

    //TODO to validate the form
    @RequestMapping(value = CREATE_RESTAURANT_ENDPOINT, method = RequestMethod.POST)
    public String createRestaurant(@ModelAttribute(CREATE_RESTAURANT_FORM) @Validated CreateRestaurantForm createRestaurantForm,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CREATE_RESTAURANT_PAGE;
        }

        restaurantService.saveRestaurant(createRestaurantForm);

        return HOME_PAGE;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected CreateRestaurantFormValidator getCreateRestaurantFormValidator() {
        return createRestaurantFormValidator;
    }

    protected RestaurantService getRestaurantService() {
        return restaurantService;
    }
}
