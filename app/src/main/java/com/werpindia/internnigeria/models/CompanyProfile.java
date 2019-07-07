package com.werpindia.internnigeria.models;

public class CompanyProfile
{
    private String companyName;
    private String email;
    private String logo;

    public CompanyProfile() { }

    public CompanyProfile(String companyName, String logo) {
        this.companyName = companyName;
        this.logo = logo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
