package com.example.codingtaskimran.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("price_type")
    @Expose
    private String priceType;
    @SerializedName("max_price")
    @Expose
    private String maxPrice;
    @SerializedName("min_price")
    @Expose
    private String minPrice;
    @SerializedName("min_discounted_price")
    @Expose
    private String minDiscountedPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMinDiscountedPrice() {
        return minDiscountedPrice;
    }

    public void setMinDiscountedPrice(String minDiscountedPrice) {
        this.minDiscountedPrice = minDiscountedPrice;
    }
}
