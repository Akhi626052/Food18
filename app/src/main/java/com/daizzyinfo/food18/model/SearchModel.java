package com.daizzyinfo.food18.model;

public class SearchModel {

    int sImg;
    String sFName,sExtra,sRating,sPrice;

    public SearchModel(int sImg, String sFName, String sExtra, String sRating, String sPrice) {
        this.sImg = sImg;
        this.sFName = sFName;
        this.sExtra = sExtra;
        this.sRating = sRating;
        this.sPrice = sPrice;
    }

    public int getsImg() {
        return sImg;
    }

    public void setsImg(int sImg) {
        this.sImg = sImg;
    }

    public String getsFName() {
        return sFName;
    }

    public void setsFName(String sFName) {
        this.sFName = sFName;
    }

    public String getsExtra() {
        return sExtra;
    }

    public void setsExtra(String sExtra) {
        this.sExtra = sExtra;
    }

    public String getsRating() {
        return sRating;
    }

    public void setsRating(String sRating) {
        this.sRating = sRating;
    }

    public String getsPrice() {
        return sPrice;
    }

    public void setsPrice(String sPrice) {
        this.sPrice = sPrice;
    }
}
