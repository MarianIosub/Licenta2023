package com.takeaseat.service.email.impl;

import com.takeaseat.model.User;
import com.takeaseat.service.email.EmailComposer;
import org.springframework.mail.SimpleMailMessage;

public class EmailComposerImpl implements EmailComposer {

    @Override
    public SimpleMailMessage composeWelcomeMessage(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@takeAsEAT.com");
        message.setTo(user.getMail());
        message.setSubject("Welcome to TakeAsEAT, " + user.getName() + " " + user.getSurname() + " :)");
        message.setText("Dear " + user.getName() + ",\n\nYour account on TakeAsEAT has been created successfully, from now you can use our services!\n\n\nThank you,\nTakeAsEAT TEAM");
        return message;
    }
}
