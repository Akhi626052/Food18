package com.daizzyinfo.food18.viewModel.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalProduct")
    @Expose
    private String totalProduct;
    @SerializedName("child")
    @Expose
    private List<SubCategoryModel> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(String totalProduct) {
        this.totalProduct = totalProduct;
    }

    public List<SubCategoryModel> getChild() {
        return child;
    }

    public void setChild(List<SubCategoryModel> child) {
        this.child = child;
    }

}
