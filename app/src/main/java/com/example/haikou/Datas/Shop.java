package com.example.haikou.Datas;

public class Shop {
    private String name;
    private int imageId;

    public Shop(String name, int imageId){
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
