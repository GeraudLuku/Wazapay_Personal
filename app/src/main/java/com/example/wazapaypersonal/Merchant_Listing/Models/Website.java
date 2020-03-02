package com.example.wazapaypersonal.Merchant_Listing.Models;

public class Website {
    String website_name, website_url, website_description;

    public Website(String website_name, String website_url, String website_description) {
        this.website_name = website_name;
        this.website_url = website_url;
        this.website_description = website_description;
    }

    public String getWebsite_name() {
        return website_name;
    }

    public void setWebsite_name(String website_name) {
        this.website_name = website_name;
    }

    public String getWebsite_url() {
        return website_url;
    }

    public void setWebsite_url(String website_url) {
        this.website_url = website_url;
    }

    public String getWebsite_description() {
        return website_description;
    }

    public void setWebsite_description(String website_description) {
        this.website_description = website_description;
    }
}
