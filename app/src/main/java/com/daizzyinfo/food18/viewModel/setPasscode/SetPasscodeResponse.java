package com.daizzyinfo.food18.viewModel.setPasscode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetPasscodeResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SetPasscodeModel data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SetPasscodeModel getData() {
        return data;
    }

    public void setData(SetPasscodeModel data) {
        this.data = data;
    }

}
