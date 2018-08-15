package com.playbasis.pb.mobileguide.data.entity;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mobile extends RealmObject {

    @PrimaryKey
    private String id;
    private String price;
    private String brand;
    private String name;
    private String thumbImageURL;
    private String description;
    private double rating;
    private boolean isFavorite;

    public Mobile(){

    }

    public Mobile(String id, String price, String brand, String name, String thumbImageURL, String description, double rating)  {

        this.id = id;
        this.price = price;
        this.brand = brand;
        this.name = name;
        this.thumbImageURL = thumbImageURL;
        this.description = description;
        this.rating = rating;

    }


    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbImageURL(String thumbImageURL) {
        this.thumbImageURL = thumbImageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }



}
