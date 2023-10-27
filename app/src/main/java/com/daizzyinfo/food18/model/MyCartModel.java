package com.daizzyinfo.food18.model;

public class MyCartModel {

    int MyCartImg;
    String MyCFName,MCFExtra,MCFPrice;

    public int getMyCartImg() {
        return MyCartImg;
    }

    public void setMyCartImg(int myCartImg) {
        MyCartImg = myCartImg;
    }

    public String getMyCFName() {
        return MyCFName;
    }

    public void setMyCFName(String myCFName) {
        MyCFName = myCFName;
    }

    public String getMCFExtra() {
        return MCFExtra;
    }

    public void setMCFExtra(String MCFExtra) {
        this.MCFExtra = MCFExtra;
    }


    public String getMCFPrice() {
        return MCFPrice;
    }

    public void setMCFPrice(String MCFPrice) {
        this.MCFPrice = MCFPrice;
    }

    public MyCartModel(int myCartImg, String myCFName, String MCFExtra, String MCFRating, String MCFPrice) {
        MyCartImg = myCartImg;
        MyCFName = myCFName;
        this.MCFExtra = MCFExtra;
        this.MCFPrice = MCFPrice;

    }
}
