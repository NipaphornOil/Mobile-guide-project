package com.playbasis.pb.mobilephonescb.presentation.detail;

public class MobileDetailViewModel {

    private String name;
    private String brand;
    private String description;

    public MobileDetailViewModel(String name, String brand, String description){
        this.name = name;
        this.brand = brand;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
