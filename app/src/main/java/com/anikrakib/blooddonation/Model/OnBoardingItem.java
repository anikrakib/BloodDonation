package com.anikrakib.blooddonation.Model;

public class OnBoardingItem {
    private int image;
    private String name;
    private String color;

    public OnBoardingItem(int image, String name, String color) {
        this.image = image;
        this.name = name;
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
