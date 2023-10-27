package com.daizzyinfo.food18.viewModel.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListModel {

    @SerializedName("popular")
    @Expose
    private List<Popular> popular;
    @SerializedName("normal")
    @Expose
    private List<Normal> normal;

    public List<Popular> getPopular() {
        return popular;
    }

    public void setPopular(List<Popular> popular) {
        this.popular = popular;
    }

    public List<Normal> getNormal() {
        return normal;
    }

    public void setNormal(List<Normal> normal) {
        this.normal = normal;
    }

}
