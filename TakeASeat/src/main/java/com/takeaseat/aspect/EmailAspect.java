package com.takeaseat.aspect;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.controller.form.RegisterForm;
import com.takeaseat.model.Order;
import com.takeaseat.model.User;
import com.takeaseat.service.EmailService;
import com.takeaseat.service.OrderService;
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
    private final OrderService orderService;

    @After("execution(* com.takeaseat.service.impl.UserServiceImpl.registerUser(..))")
    public void sendEmailAfterRegisterUser(JoinPoint joinPoint) {
        User user = userService.findByMail(getUserMailFromJoinPointAfterRegister(joinPoint));

        emailService.sendWelcomeEmail(user);
    }

    @After("execution(* com.takeaseat.dao.impl.OrderDaoImpl.save(..))")
    public void sendOrderEmails(JoinPoint joinPoint){
        Order order = (Order)joinPoint.getArgs()[0];

        emailService.sendOrderConfirmationEmail(order);
        emailService.sendReservationEmail(order);
    }

    @After("execution(* com.takeaseat.service.impl.OrderServiceImpl.acceptOrder(..))")
    public void sendAcceptOrderEmail(JoinPoint joinPoint){
        Long orderId = (Long) joinPoint.getArgs()[0];
        Order order = orderService.getOrderForId(orderId);

        emailService.sendAcceptOrderEmail(order);
    }

    @After("execution(* com.takeaseat.service.impl.OrderServiceImpl.refuseOrder(..))")
    public void sendRefuseOrderEmail(JoinPoint joinPoint){
        Long orderId = (Long) joinPoint.getArgs()[0];
        Order order = orderService.getOrderForId(orderId);

        emailService.sendRefuseOrderEmail(order);
    }

    private String getUserMailFromJoinPointAfterRegister(JoinPoint joinPoint) {
        Object object = joinPoint.getArgs()[0];
        RegisterForm registerForm = ((RegisterForm) object);

        return registerForm.getMail();
    }
}
