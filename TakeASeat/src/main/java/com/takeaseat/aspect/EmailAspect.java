package com.takeaseat.aspect;

import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.model.User;
import com.takeaseat.service.EmailService;
import com.takeaseat.service.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
@AllArgsConstructor
public class EmailAspect {

    private final EmailService emailService;
    private final UserService userService;

    @After("execution(* com.takeaseat.service.impl.UserServiceImpl.registerUser(..))")
    public void sendEmailAfterRegisterUser(JoinPoint joinPoint) {
        User user = userService.findByMail(getUserMailFromJoinPointAfterRegister(joinPoint));

        emailService.sendWelcomeEmail(user);
    }

    private String getUserMailFromJoinPointAfterRegister(JoinPoint joinPoint) {
        Object object = joinPoint.getArgs()[0];
        RegisterForm registerForm = ((RegisterForm) object);

        return registerForm.getMail();
    }
}
