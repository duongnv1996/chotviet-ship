package com.skynet.choviet.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopDetail  {
    @SerializedName("shop_info")
   Shop Shop;
     @SerializedName("list_product")
    List<Product> listProduct;
     @SerializedName("rating")
    List<Rate> rate;

    public com.skynet.choviet.models.Shop getShop() {
        return Shop;
    }

    public void setShop(com.skynet.choviet.models.Shop shop) {
        Shop = shop;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public List<Rate> getRate() {
        return rate;
    }

    public void setRate(List<Rate> rate) {
        this.rate = rate;
    }
}
