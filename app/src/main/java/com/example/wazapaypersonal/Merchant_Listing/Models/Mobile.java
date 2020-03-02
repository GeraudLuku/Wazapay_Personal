package com.example.wazapaypersonal.Merchant_Listing.Models;

public class Mobile {
    private String app_name, playstore_link, appstore_link, description;

    public Mobile(String app_name, String playstore_link, String appstore_link, String description) {
        this.app_name = app_name;
        this.playstore_link = playstore_link;
        this.appstore_link = appstore_link;
        this.description = description;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPlaystore_link() {
        return playstore_link;
    }

    public void setPlaystore_link(String playstore_link) {
        this.playstore_link = playstore_link;
    }

    public String getAppstore_link() {
        return appstore_link;
    }

    public void setAppstore_link(String appstore_link) {
        this.appstore_link = appstore_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
