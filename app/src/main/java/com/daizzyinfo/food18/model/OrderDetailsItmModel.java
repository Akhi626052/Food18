package com.daizzyinfo.food18.model;

public class OrderDetailsItmModel {

    int Fimg;
    String Fname,ExtraFname,Price,Quantity;

    public OrderDetailsItmModel(int fimg, String fname, String extraFname, String price, String quantity) {
        Fimg = fimg;
        Fname = fname;
        ExtraFname = extraFname;
        Price = price;
        Quantity = quantity;
    }

    public int getFimg() {
        return Fimg;
    }

    public void setFimg(int fimg) {
        Fimg = fimg;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getExtraFname() {
        return ExtraFname;
    }

    public void setExtraFname(String extraFname) {
        ExtraFname = extraFname;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
