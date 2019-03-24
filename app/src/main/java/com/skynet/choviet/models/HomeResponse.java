package com.skynet.choviet.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {
    @SerializedName("banner")
    private List<Banner> banners;


    @SerializedName("suggest")
    private List<Product> suggest;
    @SerializedName("hot_product")
    private List<Product> hot_product;
    @SerializedName("list_shop")
    private List<Shop> list_shop;
    @SerializedName("auction")
    private List<Auction> auction;

    public List<Product> getSuggest() {
        return suggest;
    }

    public void setSuggest(List<Product> suggest) {
        this.suggest = suggest;
    }

    public List<Shop> getList_shop() {
        return list_shop;
    }

    public void setList_shop(List<Shop> list_shop) {
        this.list_shop = list_shop;
    }

    public List<Auction> getAuction() {
        return auction;
    }

    public void setAuction(List<Auction> auction) {
        this.auction = auction;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public List<Product> getHot_product() {
        return hot_product;
    }

    public void setHot_product(List<Product> hot_product) {
        this.hot_product = hot_product;
    }


    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }


}
