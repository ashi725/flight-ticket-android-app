package com.example.myapplication.util;

import java.util.List;

public class Country {

    private String name;
    private List<String> images;
    private String description;

    // Constructor
    public Country(String name, List<String> images, String description){
        this.name = name;
        this.images = images;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }
}
