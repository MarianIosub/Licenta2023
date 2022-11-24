package com.takeaseat.service.email;

import com.takeaseat.model.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailComposer {
    SimpleMailMessage composeWelcomeMessage(User user);
}
