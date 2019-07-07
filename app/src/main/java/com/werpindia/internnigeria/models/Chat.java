package com.werpindia.internnigeria.models;

public class Chat {

    private String employerId;
    private String applicantId;
    private String internshipName;

    public Chat() {
    }

    public Chat(String employerId, String applicantId, String internshipName) {
        this.employerId = employerId;
        this.applicantId = applicantId;
        this.internshipName = internshipName;
    }

    public String getApplicantId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getInternshipName() {
        return internshipName;
    }

    public void setInternshipName(String internshipName) {
        this.internshipName = internshipName;
    }

    public String getId() {
        return getEmployerId() + getApplicantId();
    }
}
