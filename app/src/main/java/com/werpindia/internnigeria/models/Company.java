package com.werpindia.internnigeria.models;

public class Company {
    private String password;
    private String phoneNumber;
    private String email;
    private CompanyProfile profile;

    public Company() {}

    public Company(CompanyProfile profile, String password, String phoneNumber,String email) {
        this.password = password;
        this.profile = profile;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CompanyProfile getProfile() {
        return profile;
    }

    public void setProfile(CompanyProfile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
