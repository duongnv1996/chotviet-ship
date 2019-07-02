package com.skynet.chovietship.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShiperResponse {
    @SerializedName("list_shiper")
    List<Profile> list;
    @SerializedName("booking_id")
    int bookingId;
    @SerializedName("idShipers")
    String idShipers;
    @SerializedName("idShop")
    String idShop;
    @SerializedName("idShipReceived")
    String idShipReceived;

    public String getIdShipReceived() {
        return idShipReceived;
    }

    public void setIdShipReceived(String idShipReceived) {
        this.idShipReceived = idShipReceived;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getIdShipers() {
        return idShipers;
    }

    public void setIdShipers(String idShipers) {
        this.idShipers = idShipers;
    }

    public List<Profile> getList() {
        return list;
    }

    public void setList(List<Profile> list) {
        this.list = list;
    }
}
