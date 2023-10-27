package com.daizzyinfo.food18.viewModel.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductListResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ProductListModel data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ProductListModel getData() {
        return data;
    }

    public void setData(ProductListModel data) {
        this.data = data;
    }


}
