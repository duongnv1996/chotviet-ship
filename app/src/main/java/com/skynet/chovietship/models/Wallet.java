package com.skynet.chovietship.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Wallet {

    @Expose
    @SerializedName("week")
    private WeekEntity week;
    @Expose
    @SerializedName("today")
    private TodayEntity today;

    public WeekEntity getWeek() {
        return week;
    }

    public void setWeek(WeekEntity week) {
        this.week = week;
    }

    public TodayEntity getToday() {
        return today;
    }

    public void setToday(TodayEntity today) {
        this.today = today;
    }

    public static class WeekEntity {
        @Expose
        @SerializedName("account")
        private double account;
        @Expose
        @SerializedName("total_booking")
        private int total_booking;
        @Expose
        @SerializedName("distance")
        private double distance;
        @Expose
        @SerializedName("total_money")
        private double total_money;

        public double getAccount() {
            return account;
        }

        public void setAccount(double account) {
            this.account = account;
        }

        public int getTotal_booking() {
            return total_booking;
        }

        public void setTotal_booking(int total_booking) {
            this.total_booking = total_booking;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getTotal_money() {
            return total_money;
        }

        public void setTotal_money(int total_money) {
            this.total_money = total_money;
        }
    }

    public static class TodayEntity {
        @Expose
        @SerializedName("account")
        private double account;
        @Expose
        @SerializedName("total_booking")
        private int total_booking;
        @Expose
        @SerializedName("distance")
        private double distance;
        @Expose
        @SerializedName("total_money")
        private double total_money;

        public double getAccount() {
            return account;
        }

        public void setAccount(double account) {
            this.account = account;
        }

        public int getTotal_booking() {
            return total_booking;
        }

        public void setTotal_booking(int total_booking) {
            this.total_booking = total_booking;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getTotal_money() {
            return total_money;
        }

        public void setTotal_money(int total_money) {
            this.total_money = total_money;
        }
    }
}
