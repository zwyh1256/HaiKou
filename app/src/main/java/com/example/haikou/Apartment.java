package com.example.haikou;

public class Apartment {
    private String name;
    private int imageId;

    public Apartment(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
