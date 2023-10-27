package com.daizzyinfo.food18.viewModel.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<CatModel> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<CatModel> getData() {
        return data;
    }

    public void setData(List<CatModel> data) {
        this.data = data;
    }


}
