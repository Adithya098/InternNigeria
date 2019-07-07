package com.werpindia.internnigeria.models;

import com.google.firebase.Timestamp;

public class Message {

    private String employerId;
    private String applicantId;
    private Timestamp timestamp;
    private String message;
    private String chatId;


    public Message(String employerId, String applicantId, Timestamp timestamp, String message , String chatId) {
        this.employerId = employerId;
        this.applicantId = applicantId;
        this.timestamp = timestamp;
        this.message = message;
        this.chatId = chatId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
