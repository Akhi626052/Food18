package com.daizzyinfo.food18.model;

public class MyAddModel {

    String username, number,address,city,state;

    public MyAddModel(String username, String number, String address, String city, String state) {
        this.username = username;
        this.number = number;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
