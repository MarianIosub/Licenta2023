package com.takeaseat.service.email;

import com.takeaseat.model.Order;
import com.takeaseat.model.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailComposer {
    SimpleMailMessage composeWelcomeMessage(User user);

    SimpleMailMessage composeOrderConfirmationMessage(Order order);

    SimpleMailMessage composeReservationMessage(Order order);

    SimpleMailMessage composeAcceptOrderMessage(Order order);

    SimpleMailMessage composeRefuseOrderMessage(Order order);

    SimpleMailMessage composeRecoverPasswordEmail(User user, String passwordGenerated);
}
