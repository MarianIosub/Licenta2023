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
}
