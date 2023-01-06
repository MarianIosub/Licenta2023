package com.takeaseat.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.takeaseat.controller.dto.ChargeRequest;
import com.takeaseat.service.PaymentService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.takeaseat.constants.StringConstants.AMOUNT;
import static com.takeaseat.constants.StringConstants.CURRENCY;
import static com.takeaseat.constants.StringConstants.DESCRIPTION;
import static com.takeaseat.constants.StringConstants.SOURCE;
import static com.takeaseat.constants.StringConstants.STRIPE_API_SECRET_KEY;

public class PaymentServiceImpl implements PaymentService {

    @PostConstruct
    public void init() {
        Stripe.apiKey = STRIPE_API_SECRET_KEY;
    }

    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put(AMOUNT, chargeRequest.getAmount());
        chargeParams.put(CURRENCY, chargeRequest.getCurrency().toString().toLowerCase());
        chargeParams.put(DESCRIPTION, chargeRequest.getDescription());
        chargeParams.put(SOURCE, chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}
