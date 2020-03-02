package com.example.wazapaypersonal.Agent_Listing.Model;

public class Agent {
    String name, max_trans_amount;
    double lat;
    double lng;


    public Agent(String name, String max_trans_amount, double lat, double lng) {
        this.name = name;
        this.max_trans_amount = max_trans_amount;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMax_trans_amount() {
        return max_trans_amount;
    }

    public void setMax_trans_amount(String max_trans_amount) {
        this.max_trans_amount = max_trans_amount;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
