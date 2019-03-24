package com.werpindia.internnigeria.models;

public class Employer
{
    private String companyName;
    private String email;
    private String password;
    private String id;
    private String phoneNumber;

    public Employer() {}

    public Employer(String companyName, String email, String password, String phoneNumber) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
