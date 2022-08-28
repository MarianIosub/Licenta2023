package com.takeaseat.controller;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.validator.RegisterFormValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.takeaseat.constants.EndpointsConstants.HOME_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.REGISTER_ENDPOINT;
import static com.takeaseat.constants.ViewsConstants.REGISTER_PAGE;

@Controller
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    RegisterFormValidator registerFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(registerFormValidator);
    }

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.GET)
    public String getRegisterPage(@ModelAttribute("registerForm") RegisterForm registerForm) {
        return REGISTER_PAGE;
    }

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("registerForm") @Validated RegisterForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTER_PAGE;
        }
        return "redirect:" + HOME_ENDPOINT;
    }
}
