package com.lakens.shareameal.domain;

public class Cook {
    private String name;
    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;

    public Cook(String name, String city, String street, String phoneNumber, String emailAddress) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
