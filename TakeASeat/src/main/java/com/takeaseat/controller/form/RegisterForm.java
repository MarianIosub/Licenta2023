package com.takeaseat.controller.form;

public class RegisterForm {
    public String mail;
    public String name;
    public String surname;
    public String password;
    public String confirmPassword;
    public String role;
    public boolean terms;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean isTerms() {
        return terms;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }
}
