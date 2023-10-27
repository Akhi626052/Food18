package com.daizzyinfo.food18.model;

public class PopularModel {

    int PopImg;
    String PopFName,PopFRs,PopFRsOff,PopExtraF,PopRate;

    public int getPopImg() {
        return PopImg;
    }

    public void setPopImg(int popImg) {
        PopImg = popImg;
    }

    public String getPopFName() {
        return PopFName;
    }

    public void setPopFName(String popFName) {
        PopFName = popFName;
    }

    public String getPopFRs() {
        return PopFRs;
    }

    public void setPopFRs(String popFRs) {
        PopFRs = popFRs;
    }

    public String getPopFRsOff() {
        return PopFRsOff;
    }

    public void setPopFRsOff(String popFRsOff) {
        PopFRsOff = popFRsOff;
    }

    public String getPopExtraF() {
        return PopExtraF;
    }

    public void setPopExtraF(String popExtraF) {
        PopExtraF = popExtraF;
    }

    public String getPopRate() {
        return PopRate;
    }

    public void setPopRate(String popRate) {
        PopRate = popRate;
    }

    public PopularModel(int popImg, String popFName, String popFRs, String popFRsOff, String popExtraF, String popRate) {
        PopImg = popImg;
        PopFName = popFName;
        PopFRs = popFRs;
        PopFRsOff = popFRsOff;
        PopExtraF = popExtraF;
        PopRate = popRate;
    }
}
