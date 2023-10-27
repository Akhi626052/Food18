package com.daizzyinfo.food18.viewModel.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Popular {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("tax_value")
    @Expose
    private String taxValue;
    @SerializedName("net_price")
    @Expose
    private String netPrice;
    @SerializedName("prepration_time")
    @Expose
    private String preprationTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("totalSales")
    @Expose
    private String totalSales;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("subcategory_Name")
    @Expose
    private String subcategoryName;
    @SerializedName("isInCart")
    @Expose
    private Integer isInCart;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(String taxValue) {
        this.taxValue = taxValue;
    }

    public String getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice = netPrice;
    }

    public String getPreprationTime() {
        return preprationTime;
    }

    public void setPreprationTime(String preprationTime) {
        this.preprationTime = preprationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Integer getIsInCart() {
        return isInCart;
    }

    public void setIsInCart(Integer isInCart) {
        this.isInCart = isInCart;
    }

}
