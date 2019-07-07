package com.werpindia.internnigeria.models;

import java.util.List;

public class Internship {
    private String id;
    private String category;
    private String location;
    private Integer numOfOpenings;
    private String startDate;
    private String duration;
    private List<String> internResponsibilities;
    private List<String> questions;
    private Double stipend;
    private CompanyProfile profile;

    public Internship() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumOfOpenings() {
        return numOfOpenings;
    }

    public void setNumOfOpenings(Integer numOfOpenings) {
        this.numOfOpenings = numOfOpenings;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getInternResponsibilities() {
        return internResponsibilities;
    }

    public void setInternResponsibilities(List<String> internResponsibilities) {
        this.internResponsibilities = internResponsibilities;
    }

    public Double getStipend() {
        return stipend;
    }

    public void setStipend(Double stipend) {
        this.stipend = stipend;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public CompanyProfile getProfile() {
        return profile;
    }

    public void setProfile(CompanyProfile profile) {
        this.profile = profile;
    }
}
