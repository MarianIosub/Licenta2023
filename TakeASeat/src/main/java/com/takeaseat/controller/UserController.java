package com.takeaseat.controller;

import com.takeaseat.controller.form.LoginForm;
import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.validator.RegisterFormValidator;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static com.takeaseat.constants.EndpointsConstants.*;
import static com.takeaseat.constants.StringConstants.LOGIN_FORM;
import static com.takeaseat.constants.StringConstants.REGISTER_FORM;
import static com.takeaseat.constants.ViewsConstants.LOGIN_PAGE;
import static com.takeaseat.constants.ViewsConstants.REGISTER_PAGE;

@Controller
@AllArgsConstructor
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    RegisterFormValidator registerFormValidator;
    UserService userService;

    @InitBinder(REGISTER_FORM)
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(registerFormValidator);
    }

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.GET)
    public String getRegisterPage(@ModelAttribute(REGISTER_FORM) RegisterForm registerForm, Model model) {
        model.addAttribute(REGISTER_FORM, registerForm);
        return REGISTER_PAGE;
    }

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(REGISTER_FORM) @Validated RegisterForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTER_PAGE;
        }
        userService.registerUser(form);
        return REDIRECT + HOME_ENDPOINT;
    }

    @RequestMapping(value = LOGIN_ENDPOINT, method = RequestMethod.GET)
    public String getLoginPage(@ModelAttribute(LOGIN_FORM) LoginForm loginForm,
                               @RequestParam(value = "error", required = false) String error,
                               Model model) {
        LOG.error(error);
        model.addAttribute(LOGIN_FORM, loginForm);
        return LOGIN_PAGE;
    }
}
