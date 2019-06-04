package com.example.haikou;

public class Amuse {
    private String name;
    private int imageId;

    public Amuse(String name, int imageId){
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
