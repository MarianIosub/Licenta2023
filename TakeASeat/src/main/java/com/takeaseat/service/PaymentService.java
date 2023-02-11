package com.takeaseat.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.takeaseat.controller.dto.ChargeRequest;


/**
 * This interface defines the methods for a Payment service.
 *
 * @author Marian Iosub
 * @version 1.0
 */
public interface PaymentService {

	/**
	 * Charges a payment with a specified ChargeRequest.
	 *
	 * @param chargeRequest the request containing the payment information
	 *
	 * @return the charge object containing the charge details
	 *
	 * @throws StripeException if the charge fails
	 */
	Charge charge(ChargeRequest chargeRequest) throws StripeException;
}
