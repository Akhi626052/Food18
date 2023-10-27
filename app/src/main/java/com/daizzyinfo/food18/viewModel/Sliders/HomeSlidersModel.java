package com.daizzyinfo.food18.viewModel.Sliders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeSlidersModel {

    @SerializedName("top_slider")
    @Expose
    private List<TopSlider> topSlider;
    @SerializedName("bottom_slider")
    @Expose
    private List<BottomSlider> bottomSlider;

    public List<TopSlider> getTopSlider() {
        return topSlider;
    }

    public void setTopSlider(List<TopSlider> topSlider) {
        this.topSlider = topSlider;
    }

    public List<BottomSlider> getBottomSlider() {
        return bottomSlider;
    }

    public void setBottomSlider(List<BottomSlider> bottomSlider) {
        this.bottomSlider = bottomSlider;
    }
}
