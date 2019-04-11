package com.skynet.chovietship.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class History {

    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("payment")
    private int payment;
    @Expose
    @SerializedName("note")
    private String note;
    @Expose
    @SerializedName("price")
    private double price;
    @Expose
    @SerializedName("id_promotion")
    private int id_promotion;
    @Expose
    @SerializedName("date_booking")
    private String date_booking;
    @Expose
    @SerializedName("time_ship")
    private String time_ship;
    @Expose
    @SerializedName("date_create")
    private String date_create;
    @Expose
    @SerializedName("shop_id")
    private int shop_id;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("start")
    private String start;
    @Expose
    @SerializedName("end")
    private String end;
    @Expose
    @SerializedName("lat")
    private double latFrom;
    @Expose
    @SerializedName("lng")
    private double lngFrom;
    @Expose
    @SerializedName("latTo")
    private double latTo;
    @Expose
    @SerializedName("lngTo")
    private double lngTo;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("time_receive")
    private String time_receive;
    @Expose
    @SerializedName("time_finish")
    private String time_finish;
    @Expose
    @SerializedName("price_ship")
    private Double price_ship;
    @Expose
    @SerializedName("distance")
    private Double distance;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("avatar")
    private String avatar;    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("user")
    private Profile user;
    @Expose
    @SerializedName("list_product")
    private List<Product> list_product;
    private String activeString;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPrice_ship() {
        return price_ship;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getLatFrom() {
        return latFrom;
    }

    public String getTime_receive() {
        return time_receive;
    }

    public void setTime_receive(String time_receive) {
        this.time_receive = time_receive;
    }

    public String getTime_finish() {
        return time_finish;
    }

    public void setTime_finish(String time_finish) {
        this.time_finish = time_finish;
    }

    public void setLatFrom(double latFrom) {
        this.latFrom = latFrom;
    }

    public double getLngFrom() {
        return lngFrom;
    }

    public void setLngFrom(double lngFrom) {
        this.lngFrom = lngFrom;
    }

    public double getLatTo() {
        return latTo;
    }

    public void setLatTo(double latTo) {
        this.latTo = latTo;
    }

    public double getLngTo() {
        return lngTo;
    }

    public void setLngTo(double lngTo) {
        this.lngTo = lngTo;
    }

    public void setPrice_ship(Double price_ship) {
        this.price_ship = price_ship;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getActiveString() {
        return activeString;
    }

    public void setActiveString(String activeString) {
        this.activeString = activeString;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Product> getList_product() {
        return list_product;
    }

    public void setList_product(List<Product> list_product) {
        this.list_product = list_product;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPayment() {
        return payment;
    }

    public String getTime_ship() {
        return time_ship;
    }

    public void setTime_ship(String time_ship) {
        this.time_ship = time_ship;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public String getDate_booking() {
        return date_booking;
    }

    public void setDate_booking(String date_booking) {
        this.date_booking = date_booking;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
