package com.example.myapplication.util;

public class Country {

    private String name;
    private String image1;
    private String image2;
    private String image3;
    private String description;
    private String continent;

    // Constructor
    public Country(String name, String image1, String image2, String image3, String description, String continent){
        this.name = name;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.description = description;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage1() { return image1; }
    public String getImage2() { return image2; }
    public String getImage3() { return image3; }

    public String getContinentName() { return continent; }
}