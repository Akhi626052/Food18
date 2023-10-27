package com.daizzyinfo.food18.viewModel.City;

import com.daizzyinfo.food18.viewModel.City.CityModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<CityModel> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<CityModel> getData() {
        return data;
    }

    public void setData(List<CityModel> data) {
        this.data = data;
    }


}



