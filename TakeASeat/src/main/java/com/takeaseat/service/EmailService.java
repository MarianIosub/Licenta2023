package com.takeaseat.service;

import com.takeaseat.controller.dto.Cart;
import com.takeaseat.model.Order;
import com.takeaseat.model.User;

public interface EmailService {
    void sendWelcomeEmail(final User user);

    void sendOrderConfirmationEmail(final Order order);

    void sendReservationEmail(final Order order);

    void sendAcceptOrderEmail(final Order order);

    void sendRefuseOrderEmail(final Order order);
}
