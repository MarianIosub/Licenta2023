package com.takeaseat.service;

import com.takeaseat.model.User;

public interface EmailService {
    void sendWelcomeEmail(final User user);
}
