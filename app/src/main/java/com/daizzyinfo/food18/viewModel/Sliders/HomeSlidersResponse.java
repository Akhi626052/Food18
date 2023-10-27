package com.daizzyinfo.food18.viewModel.Sliders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeSlidersResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private HomeSlidersModel data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public HomeSlidersModel getData() {
        return data;
    }

    public void setData(HomeSlidersModel data) {
        this.data = data;
    }

}
