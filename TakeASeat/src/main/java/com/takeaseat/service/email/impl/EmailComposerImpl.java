package com.takeaseat.service.email.impl;

import com.takeaseat.model.Order;
import com.takeaseat.model.User;
import com.takeaseat.service.email.EmailComposer;
import org.springframework.mail.SimpleMailMessage;

import java.time.format.DateTimeFormatter;


public class EmailComposerImpl implements EmailComposer {

	@Override
	public SimpleMailMessage composeWelcomeMessage(User user) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@takeAsEAT.com");
		message.setTo(user.getMail());
		message.setSubject("Welcome to TakeAsEAT, " + user.getName() + " " + user.getSurname() + " :)");
		message.setText("Dear " + user.getName()
								+ ",\n\nYour account on TakeAsEAT has been created successfully, from now you can use our "
								+ "services!\n\n\nThank you,\nTakeAsEAT TEAM");
		return message;
	}

	@Override
	public SimpleMailMessage composeOrderConfirmationMessage(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@takeAsEAT.com");
		message.setTo(order.getUser().getMail());
		message.setSubject("Order confirmation at " + order.getRestaurant().getName() + " on " + order.getDate()
																									  .format(DateTimeFormatter.ofPattern(
																											  "dd/MM/yyyy")));
		message.setText(
				"Dear " + order.getUser().getName() + " " + order.getUser().getSurname() + ",\n\n" +
						"Thank you for placing an order with us. We are pleased to confirm that your order has been received and"
						+ " is being processed.\n"
						+
						"\n" +
						"Order details:\n" +
						"\n" +
						"Order number: " + order.getId() + "\n" +
						"Restaurant: " + order.getRestaurant().getName() + "\n" +
						"Date: " + order.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
						"Starting hour: " + order.getStartingHour().toString().replace('.', ':') + "0\n" +
						"Ending hour: " + order.getEndingHour().toString().replace('.', ':') + "0\n" +
						"Total price: " + order.getTotalPrice() + " RON\n" +
						"Payment method: " + order.getCardPaymentNetwork() + " ending in " + order.getCardLastDigits()
						+ " (expires " + order.getCardExpirationMonth() + "/" + order.getCardExpirationYear() + ")\n" +
						"Transaction status: " + order.getTransactionStatus() + "\n" +
						"We will send you another email once your order is approved or denied by " + order.getRestaurant()
																										  .getName()
						+ " administrator.\n" +
						"You can also check the status in your account!\n" +
						"\n" +
						"Thank you for choosing us and we hope you enjoy your meal!\n" +
						"\n" +
						"Best regards, \nTakeAsEAT Team");
		return message;
	}

	@Override
	public SimpleMailMessage composeReservationMessage(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@takeAsEAT.com");
		message.setTo(order.getRestaurant().getMail());
		message.setSubject("New Order from " + order.getUser().getName() + " - Order no #" + order.getId());
		message.setText("Dear " + order.getRestaurant().getName() + " Team,\n" +
								"\n" +
								"We would like to inform you that a new order has been placed at your restaurant by "
								+ order.getUser().getName() + ". Please find the order details below:\n" +
								"\n" +
								"Order number: " + order.getId() + "\n" +
								"User: " + order.getUser().getName() + " " + order.getUser().getSurname() + " (" + order.getUser()
																														.getMail()
								+ ")\n" +
								"Date: " + order.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
								"Starting hour: " + order.getStartingHour().toString().replace('.', ':') + "0\n" +
								"Ending hour: " + order.getEndingHour().toString().replace('.', ':') + "0\n" +
								"Total price: " + order.getTotalPrice() + " RON\n" +
								"Payment method: " + order.getCardPaymentNetwork() + " ending in " + order.getCardLastDigits()
								+ " (expires " + order.getCardExpirationMonth() + "/" + order.getCardExpirationYear() + ")\n" +
								"Transaction status: " + order.getTransactionStatus() + "\n" +
								"\n" +
								"We kindly ask you to approve or deny this reservation as soon as possible from our application"
								+ ".\n"
								+
								"Thank you for your cooperation!\n" +
								"\n" +
								"Best regards,\n" +
								"TakeAsEAT Team");

		return message;
	}

	@Override
	public SimpleMailMessage composeAcceptOrderMessage(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@takeAsEAT.com");
		message.setTo(order.getUser().getMail());
		message.setSubject("Reservation APPROVED - Order #" + order.getId());
		message.setText("Dear " + order.getUser().getName() + " " + order.getUser().getSurname() + ",\n\n" +
								"\n" +
								"Your reservation at " + order.getRestaurant().getName() + " has been approved. Order details:\n"
								+
								"\n" +
								"Order number: #" + order.getId() + "\n" +
								"Date:" + order.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
								"Starting hour: " + order.getStartingHour().toString().replace('.', ':') + "0\n" +
								"Total price: " + order.getTotalPrice() + " RON\n" +
								"Restaurant message: \"" + order.getMessage() + "\" \n" +
								"Restaurant is waiting for you at the specified date and time. If you have any special requests "
								+ "or concerns, please contact us.\n"
								+
								"\n" +
								"Thank you for choosing us and we hope you enjoy your meal!\n" +
								"\n" +
								"Best regards,\n" +
								"TakeAsEAT Team");
		return message;
	}

	@Override
	public SimpleMailMessage composeRefuseOrderMessage(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@takeAsEAT.com");
		message.setTo(order.getUser().getMail());
		message.setSubject("Reservation REFUSED - Order #" + order.getId());
		message.setText("Dear " + order.getUser().getName() + " " + order.getUser().getSurname() + ",\n\n" +
								"\n" +
								"We regret to inform you that your reservation at " + order.getRestaurant().getName()
								+ " has been refused. Order details:\n" +
								"\n" +
								"Order number: #" + order.getId() + "\n" +
								"Date:" + order.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
								"Starting hour: " + order.getStartingHour().toString().replace('.', ':') + "0\n" +
								"Total price: " + order.getTotalPrice() + " RON\n" +
								"Restaurant message: \"" + order.getMessage() + "\" \n" +
								"We apologize for any inconvenience this may have caused. Please contact us if you have any "
								+ "questions or concerns.\n"
								+
								"\n" +
								"Thank you for choosing us and we hope we'll see you another time!\n" +
								"\n" +
								"Best regards,\n" +
								"TakeAsEAT Team");
		return message;
	}

	@Override
	public SimpleMailMessage composeRecoverPasswordEmail(User user, String passwordGenerated) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@takeAsEAT.com");
		message.setTo(user.getMail());
		message.setSubject("Password Reset Request - TakeAsEAT");
		message.setText("Dear " + user.getName() + ",\n" +
								"\n" +
								"We received a request to reset the password for your TakeAsEAT account. To reset your password,"
								+ " please use the following password:\n"
								+
								"\n" +
								passwordGenerated + "\n" +
								"\n" +
								"Please log in to your account with this temporary password and update your account with a new "
								+ "one.\n"
								+
								"\n" +
								"If you did not request a password reset, please let us know immediately.\n" +
								"\n" +
								"Thank you for your attention to this matter.\n" +
								"\n" +
								"Best regards,\n" +
								"TakeAsEAT Team");

		return message;
	}
}
