package com.example.myapplication.util;

public class Country {

    private String name;
    private String image1;
    private String image2;
    private String image3;
    private String averagePrice;
    private String description;
    private String continent;

    private int favourite;


    // Constructor
    public Country(String name, String image1, String image2, String image3, String averagePrice, String description, String continent, int favourite){
        this.name = name;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.averagePrice = averagePrice;
        this.description = description;
        this.continent = continent;
        this.favourite = favourite;
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
    public String getAveragePrice() { return averagePrice; }
    public String getContinentName() { return continent; }
    public int getFavourite() { return favourite; }

    public int setFavourite(int intent) {
        if (intent > 0){
            favourite = 1;
        }
        else {
            favourite = 0;
        }
        return favourite;
    }
}
