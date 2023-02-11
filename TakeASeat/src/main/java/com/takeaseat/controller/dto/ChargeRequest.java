package com.takeaseat.controller.dto;

import lombok.Data;


@Data
public class ChargeRequest {
	private String description;
	private Integer amount;
	private Currency currency;
	private String stripeEmail;
	private String stripeToken;

	public enum Currency {
		RON;
	}


}

