package com.daizzyinfo.food18.viewModel.State;

import com.daizzyinfo.food18.viewModel.State.StateModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<StateModel> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<StateModel> getData() {
        return data;
    }

    public void setData(List<StateModel> data) {
        this.data = data;
    }
}
