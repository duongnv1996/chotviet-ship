package com.skynet.chovietship.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Shiper {

    @Expose
    @SerializedName("distance")
    private double distance;
    @Expose
    @SerializedName("lng")
    private double lng;
    @Expose
    @SerializedName("lat")
    private double lat;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("booking_id")
    private int booking_id;
    @Expose
    @SerializedName("id")
    private int id;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
