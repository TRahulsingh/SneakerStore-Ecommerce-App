package com.example.brandshoesapp.model;

import java.util.SplittableRandom;

public class DetailsNewProductsModel {
    int image;
    String name;


    public DetailsNewProductsModel() {
    }

    public DetailsNewProductsModel(int image, String name) {
        this.image = image;
        this.name = name;
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

}
