package com.example.hp.navbarapp;

/**
 * Created by hp on 09/02/2018.
 */

public class Restaurant {
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String WEIGHT = "weight";

    private String name;
    private String description;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Restaurant(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }


}
