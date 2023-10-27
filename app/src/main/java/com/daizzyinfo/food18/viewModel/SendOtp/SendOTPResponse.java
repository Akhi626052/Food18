package com.daizzyinfo.food18.viewModel.SendOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendOTPResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
