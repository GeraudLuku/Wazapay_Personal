package com.example.wazapaypersonal.Merchant_Listing.Models;

public class Taxi {
   private String name, phonenumber, location;

    public Taxi(String name, String phonenumber, String location) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
