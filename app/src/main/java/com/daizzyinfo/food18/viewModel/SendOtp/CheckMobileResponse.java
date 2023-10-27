package com.daizzyinfo.food18.viewModel.SendOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckMobileResponse {


    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }


}
