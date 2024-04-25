package com.example.travel_app;

public class ReadWriteUserDetails {

    public String fullName,dob,gender,mobile,state;

    public ReadWriteUserDetails(String fullName, String dob, String gender, String mobile, String state) {
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.mobile = mobile;
        this.state = state;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
