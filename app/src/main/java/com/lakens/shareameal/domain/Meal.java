package com.lakens.shareameal.domain;

import java.util.List;

public class Meal {
    private int id;
    private String name;
    private String description;
    private boolean isActive;
    private boolean isVega;
    private boolean isVegan;
    private boolean isToTakeHome;
    private String serveDate;
    private int maxAmountOfParticipants;
    private String price;
    private String imageUrl;
    private String allergenes;
    private Cook cook;
    private List<Meal> results = null;

    public Meal(String name, String description, String price, String imageUrl, int id, boolean isVega, boolean isVegan, String serveDate, String allergenes, Cook cook, boolean isToTakeHome, boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isVega = isVega;
        this.isVegan = isVegan;
        this.isToTakeHome = isToTakeHome;
        this.serveDate = serveDate;
        this.maxAmountOfParticipants = maxAmountOfParticipants;
        this.price = price;
        this.imageUrl = imageUrl;
        this.allergenes = allergenes;
        this.cook = cook;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isVega() { return isVega; }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isToTakeHome() {
        return isToTakeHome;
    }

    public String getServeDate() {
        return serveDate;
    }

    public int getMaxAmountOfParticipants() {
        return maxAmountOfParticipants;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAllergenes() {
        return allergenes;
    }

    public Cook getCook() { return cook; }

    public List<Meal> getResults() {
        return results;
    }
}
