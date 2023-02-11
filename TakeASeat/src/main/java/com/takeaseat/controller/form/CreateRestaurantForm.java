package com.takeaseat.controller.form;


import org.springframework.web.multipart.MultipartFile;


public class CreateRestaurantForm {

	public String name;
	public MultipartFile photo;
	public String description;
	public String city;
	public String address;
	public String mail;
	public String phoneNumber;
	public Double openingHour;
	public Double closingHour;
	public Double priceRequired;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(Double openingHour) {
		this.openingHour = openingHour;
	}

	public Double getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(Double closingHour) {
		this.closingHour = closingHour;
	}

	public Double getPriceRequired() {
		return priceRequired;
	}

	public void setPriceRequired(Double priceRequired) {
		this.priceRequired = priceRequired;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
}
