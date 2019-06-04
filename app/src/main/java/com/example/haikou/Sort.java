package com.example.haikou;

public class Sort {
    private String name;

    private int imageId;

    private int sortId;

    public Sort(String name, int imageId, int sortId) {
        this.name = name;
        this.imageId = imageId;
        this.sortId = sortId;
    }
    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getSortId() {
        return sortId;
    }

}
