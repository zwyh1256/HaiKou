package com.example.haikou;

public class Hospital {
    private String name;
    private int imageId;

    public Hospital(String name, int imageId){
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
