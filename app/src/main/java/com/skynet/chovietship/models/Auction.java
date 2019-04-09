package com.skynet.chovietship.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Auction {

    @Expose
    @SerializedName("active")
    private String active;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("step_price")
    private double step_price;
    @Expose
    @SerializedName("start_price")
    private double start_price;
    @Expose
    @SerializedName("last_price")
    private double last_price;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("brand")
    private String brand;
    @Expose
    @SerializedName("unit")
    private String unit;
    @Expose
    @SerializedName("product_name")
    private String product_name;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("shop_id")
    private int shop_id;
    @Expose
    @SerializedName("id")
    private int id;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getStep_price() {
        return step_price;
    }

    public void setStep_price(double step_price) {
        this.step_price = step_price;
    }

    public double getStart_price() {
        return start_price;
    }

    public void setStart_price(double start_price) {
        this.start_price = start_price;
    }

    public double getLast_price() {
        return last_price;
    }

    public void setLast_price(double last_price) {
        this.last_price = last_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
