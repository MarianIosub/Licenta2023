package com.takeaseat.service.impl;

import com.takeaseat.model.Order;
import com.takeaseat.model.User;
import com.takeaseat.service.EmailService;
import com.takeaseat.service.email.EmailComposer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final EmailComposer emailComposer;

    public EmailServiceImpl(final JavaMailSender mailSender, final EmailComposer emailComposer) {
        this.mailSender = mailSender;
        this.emailComposer = emailComposer;
    }

    @Override
    public void sendWelcomeEmail(final User user) {
        SimpleMailMessage message = getEmailComposer().composeWelcomeMessage(user);
        getMailSender().send(message);
    }

    @Override
    public void sendOrderConfirmationEmail(final Order order) {
        SimpleMailMessage message = getEmailComposer().composeOrderConfirmationMessage(order);

        getMailSender().send(message);
    }

    @Override
    public void sendReservationEmail(final Order order) {
        SimpleMailMessage message = getEmailComposer().composeReservationMessage(order);

        getMailSender().send(message);
    }

    @Override
    public void sendAcceptOrderEmail(final Order order) {
        SimpleMailMessage message = getEmailComposer().composeAcceptOrderMessage(order);

        getMailSender().send(message);
    }

    @Override
    public void sendRefuseOrderEmail(Order order) {
        SimpleMailMessage message = getEmailComposer().composeRefuseOrderMessage(order);

        getMailSender().send(message);
    }

    @Override
    public void sendRecoverPasswordEmail(User user, String passwordGenerated) {
        SimpleMailMessage message = getEmailComposer().composeRecoverPasswordEmail(user, passwordGenerated);

        getMailSender().send(message);
    }


    protected EmailComposer getEmailComposer() {
        return emailComposer;
    }

    protected JavaMailSender getMailSender() {
        return mailSender;
    }
}
