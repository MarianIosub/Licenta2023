package com.takeaseat.controller;

import com.takeaseat.controller.form.ForgotPasswordForm;
import com.takeaseat.controller.form.LoginForm;
import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.controller.form.UpdateProfileForm;
import com.takeaseat.controller.validator.ForgotPasswordFormValidator;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.takeaseat.constants.EndpointsConstants.FORGOT_PASSWORD_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.LOGIN_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.REDIRECT;
import static com.takeaseat.constants.EndpointsConstants.REGISTER_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.UPDATE_PROFILE_ENDPOINT;
import static com.takeaseat.constants.MessagePropertiesConstants.LOGOUT_SUCCESS_MESSAGE;
import static com.takeaseat.constants.MessagePropertiesConstants.RECOVER_PASSWORD_MAIL_SENT;
import static com.takeaseat.constants.MessagePropertiesConstants.REGISTER_SUCCESSFUL_MESSAGE;
import static com.takeaseat.constants.MessagePropertiesConstants.UPDATE_PROFILE_SUCCESS_MESSAGE;
import static com.takeaseat.constants.StringConstants.FLASH_MESSAGE;
import static com.takeaseat.constants.StringConstants.FORGOT_PASSWORD_FORM;
import static com.takeaseat.constants.StringConstants.LOGIN_ERROR;
import static com.takeaseat.constants.StringConstants.LOGIN_FORM;
import static com.takeaseat.constants.StringConstants.LOGOUT;
import static com.takeaseat.constants.StringConstants.REGISTER_FORM;
import static com.takeaseat.constants.StringConstants.UPDATE_PROFILE_FORM;
import static com.takeaseat.constants.ViewsConstants.FORGOT_PASSWORD_PAGE;
import static com.takeaseat.constants.ViewsConstants.LOGIN_PAGE;
import static com.takeaseat.constants.ViewsConstants.REGISTER_PAGE;
import static com.takeaseat.constants.ViewsConstants.UPDATE_PROFILE_PAGE;
import static com.takeaseat.helper.CreateEndpointHelper.createEndpoint;
import static java.util.Objects.nonNull;


@Controller
@AllArgsConstructor
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	private final RegisterFormValidator registerFormValidator;
	private final UpdateProfileFormValidator updateProfileFormValidator;
	private final ForgotPasswordFormValidator forgotPasswordFormValidator;
	private final UserService userService;

	@InitBinder(REGISTER_FORM)
	protected void initBinderRegister(WebDataBinder binder) {
		binder.setValidator(getRegisterFormValidator());
	}

	@InitBinder(UPDATE_PROFILE_FORM)
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(getUpdateProfileFormValidator());
	}

	@InitBinder(FORGOT_PASSWORD_FORM)
	protected void initBinderForgotPassword(WebDataBinder binder) {
		binder.setValidator(getForgotPasswordFormValidator());
	}

	@RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.GET)
	public String getRegisterPage(@ModelAttribute(REGISTER_FORM) RegisterForm registerForm, Model model) {
		model.addAttribute(REGISTER_FORM, registerForm);
		return REGISTER_PAGE;
	}

	@RequestMapping(value = REGISTER_ENDPOINT, method = RequestMethod.POST)
	public String registerUser(@ModelAttribute(REGISTER_FORM) @Validated RegisterForm form, BindingResult bindingResult,
							   Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return REGISTER_PAGE;
		}
		userService.registerUser(form);
		redirectAttributes.addFlashAttribute(FLASH_MESSAGE, REGISTER_SUCCESSFUL_MESSAGE);
		return createEndpoint(REDIRECT, LOGIN_ENDPOINT);
	}

	@RequestMapping(value = LOGIN_ENDPOINT, method = RequestMethod.GET)
	public String getLoginPage(@ModelAttribute(LOGIN_FORM) LoginForm loginForm,
							   @SessionAttribute(value = LOGIN_ERROR, required = false) String loginError,
							   @RequestParam(value = LOGOUT, required = false) String logout,
							   Model model) {
		if (nonNull(logout)) {
			model.addAttribute(FLASH_MESSAGE, LOGOUT_SUCCESS_MESSAGE);
		}
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
								BindingResult bindingResult, RedirectAttributes redirectAttributes,
								Model model) {

		if (bindingResult.hasErrors()) {
			return UPDATE_PROFILE_PAGE;
		}
		getUserService().updateCurrentUser(updateProfileForm);
		redirectAttributes.addFlashAttribute(FLASH_MESSAGE, UPDATE_PROFILE_SUCCESS_MESSAGE);
		return createEndpoint(REDIRECT, UPDATE_PROFILE_ENDPOINT);
	}

	@RequestMapping(value = FORGOT_PASSWORD_ENDPOINT, method = RequestMethod.GET)
	public String getForgotPasswordPage(@ModelAttribute(FORGOT_PASSWORD_FORM) ForgotPasswordForm forgotPasswordForm,
										Model model) {
		model.addAttribute(FORGOT_PASSWORD_FORM, forgotPasswordForm);

		return FORGOT_PASSWORD_PAGE;
	}

	@RequestMapping(value = FORGOT_PASSWORD_ENDPOINT, method = RequestMethod.POST)
	public String recoverPassword(@ModelAttribute(FORGOT_PASSWORD_FORM) @Validated ForgotPasswordForm forgotPasswordForm,
								  BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			return FORGOT_PASSWORD_PAGE;
		}

		getUserService().recoverPassword(forgotPasswordForm.getMail());

		redirectAttributes.addFlashAttribute(FLASH_MESSAGE, RECOVER_PASSWORD_MAIL_SENT);
		return createEndpoint(REDIRECT, LOGIN_ENDPOINT);
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

	protected ForgotPasswordFormValidator getForgotPasswordFormValidator() {
		return forgotPasswordFormValidator;
	}
}
