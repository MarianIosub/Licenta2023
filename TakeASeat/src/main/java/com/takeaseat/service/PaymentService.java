package com.takeaseat.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.takeaseat.controller.dto.ChargeRequest;

public interface PaymentService {
    Charge charge(ChargeRequest chargeRequest) throws StripeException;
}
