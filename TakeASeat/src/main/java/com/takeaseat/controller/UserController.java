package com.takeaseat.controller;

import com.takeaseat.controller.form.LoginForm;
import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.form.UpdateProfileForm;
import com.takeaseat.controller.validator.RegisterFormValidator;
import com.takeaseat.controller.validator.UpdateProfileFormValidator;
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
import static com.takeaseat.constants.StringConstants.*;
import static com.takeaseat.constants.ViewsConstants.*;

@Controller
@AllArgsConstructor
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final RegisterFormValidator registerFormValidator;
    private final UpdateProfileFormValidator updateProfileFormValidator;
    private final UserService userService;

    @InitBinder(REGISTER_FORM)
    protected void initBinderRegister(WebDataBinder binder) {
        binder.setValidator(getRegisterFormValidator());
    }

    @InitBinder(UPDATE_PROFILE_FORM)
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(getUpdateProfileFormValidator());
    }

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.GET)
    public String getRegisterPage(@ModelAttribute(REGISTER_FORM) RegisterForm registerForm, Model model) {
        model.addAttribute(REGISTER_FORM, registerForm);
        return REGISTER_PAGE;
    }

    @RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(REGISTER_FORM) @Validated RegisterForm form, BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTER_PAGE;
        }

        userService.registerUser(form);

        return REDIRECT + HOME_ENDPOINT;
    }

    @RequestMapping(value = LOGIN_ENDPOINT, method = RequestMethod.GET)
    public String getLoginPage(@ModelAttribute(LOGIN_FORM) LoginForm loginForm,
                               @SessionAttribute(value = LOGIN_ERROR, required = false) String loginError,
                               Model model) {
        model.addAttribute(LOGIN_FORM, loginForm);
        model.addAttribute(LOGIN_ERROR, loginError);
        return LOGIN_PAGE;
    }

    @RequestMapping(value = UPDATE_PROFILE_ENDPOINT, method = RequestMethod.GET)
    public String getUpdateProfilePage(Model model) {
        model.addAttribute(UPDATE_PROFILE_FORM, getUserService().getUpdateProfileForm());
        return UPDATE_PROFILE_PAGE;
    }

    @RequestMapping(value = UPDATE_PROFILE_ENDPOINT, method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute(UPDATE_PROFILE_FORM) @Validated UpdateProfileForm updateProfileForm,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            return UPDATE_PROFILE_PAGE;
        }
        getUserService().updateCurrentUser(updateProfileForm);
        return REDIRECT + HOME_ENDPOINT;
    }

    protected RegisterFormValidator getRegisterFormValidator() {
        return registerFormValidator;
    }

    protected UpdateProfileFormValidator getUpdateProfileFormValidator() {
        return updateProfileFormValidator;
    }

    protected UserService getUserService() {
        return userService;
    }
}
