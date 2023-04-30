package com.example.madmp.domain;

import java.io.Serializable;

public class CatDomain implements Serializable {
    private String title;
    private String pic;
    private String discription;
    private Double fee;
    private int numberInCart;

    public CatDomain(String title, String pic, String discription, Double fee) {
        this.title = title;
        this.pic = pic;
        this.discription = discription;
        this.fee = fee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public CatDomain(String title, String pic, String discription, Double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.discription = discription;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }
}
