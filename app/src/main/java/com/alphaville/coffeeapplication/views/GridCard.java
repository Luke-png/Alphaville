package com.alphaville.coffeeapplication.views;


public class GridCard {

    private String name;
    private int imgid;

    public GridCard(String name, int imgid) {
        this.name = name;
        this.imgid = imgid;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
