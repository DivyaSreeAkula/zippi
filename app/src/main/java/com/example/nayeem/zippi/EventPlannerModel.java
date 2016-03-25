package com.example.nayeem.zippi;


import android.graphics.Bitmap;

/**
 * Created by DIVYA on 1/27/2016.
 */
public class EventPlannerModel {

    public Bitmap bitmap;
    public String organisationname;
    public String name;
    public String email;
    public String pwd;
    public String phone_number;
    public String address;
    int events,otp,rating;
    public String wedding;
    public String birthday;
    public String conference;
    public String workshop;
    public String splfuncs;
    public int rowId;
    public EventPlannerModel(){}

    public EventPlannerModel(int rating,String organisationname,Bitmap bitmap)
    {
        this.organisationname=organisationname;
        this.bitmap=bitmap;
        this.rating=rating;
    }

    public EventPlannerModel(int rating,String organisationname)
    {
        this.organisationname=organisationname;

        this.rating=rating;
    }
    EventPlannerModel( Bitmap bitmap)
    {
        this.bitmap=bitmap;

    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getOrganisationname() {
        return organisationname;
    }

    public void setOrganisationname(String organisationname) {
        this.organisationname = organisationname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEvents() {
        return events;
    }

    public void setEvents(int events) {
        this.events = events;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getWedding() {
        return wedding;
    }

    public void setWedding(String wedding) {
        this.wedding = wedding;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getSplfuncs() {
        return splfuncs;
    }

    public void setSplfuncs(String splfuncs) {
        this.splfuncs = splfuncs;
    }
}

