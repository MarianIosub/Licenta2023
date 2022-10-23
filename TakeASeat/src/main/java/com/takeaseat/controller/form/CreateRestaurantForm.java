package com.takeaseat.controller.form;


public class CreateRestaurantForm {

    public String name;
    public String description;
    public String address;
    public String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
