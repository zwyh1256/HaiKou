package com.example.haikou;

public class Scene {

    private String name;
    private int imageId;

    public Scene(String name, int imageId){
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

