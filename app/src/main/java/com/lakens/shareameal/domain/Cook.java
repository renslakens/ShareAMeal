package com.lakens.shareameal.domain;

public class Cook {
    private String cookNameString;
    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;

    public Cook(String cookNameString, String city, String street, String phoneNumber, String emailAddress) {
        this.cookNameString = cookNameString;
        this.city = city;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
}
