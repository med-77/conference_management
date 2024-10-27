package com.example.project1;

import java.util.Date;

public class Conference {

    private int id;
    private String confoName;
    private String topic;
    private Date startDate;
    private Date endDate;
    private Date applyDateStart;
    private Date applyDateFinish;
    private String place;
    private Double price;

    public Conference(int id, String confoName, String topic, Date startDate, Date endDate, Date applyDateStart, Date applyDateFinish, String place, Double price) {
        this.id = id;
        this.confoName = confoName;
        this.topic = topic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applyDateStart = applyDateStart;
        this.applyDateFinish = applyDateFinish;
        this.place = place;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfoName() {
        return confoName;
    }

    public void setConfoName(String confoName) {
        this.confoName = confoName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(Date applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public Date getApplyDateFinish() {
        return applyDateFinish;
    }

    public void setApplyDateFinish(Date applyDateFinish) {
        this.applyDateFinish = applyDateFinish;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void createConference () {

    }

    public void showAllConference() {

    }

    public void showConference (int confoID) {

    }

    public void generateReport () {

    }

    public void editConference (){

    }
}
