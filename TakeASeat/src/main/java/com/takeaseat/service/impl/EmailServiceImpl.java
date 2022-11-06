package com.takeaseat.service.impl;

import com.takeaseat.model.User;
import com.takeaseat.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendWelcomeEmail(User user) {
        SimpleMailMessage message = composeWelcomeMessage(user);
        getMailSender().send(message);
    }

    private SimpleMailMessage composeWelcomeMessage(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@takeAsEAT.com");
        message.setTo(user.getMail());
        message.setSubject("Welcome to TakeAsEAT, " + user.getName() + " " + user.getSurname() + " :)");
        message.setText("Dear " +user.getName()+",\n\nYou just created an account on TakeAsEAT, from now you can use our application!\n\n\nThank you,\nTakeAsEAT TEAM");
        return message;
    }

    protected JavaMailSender getMailSender() {
        return mailSender;
    }
}
