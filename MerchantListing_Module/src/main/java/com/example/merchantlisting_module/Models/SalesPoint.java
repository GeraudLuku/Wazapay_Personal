package com.example.merchantlisting_module.Models;

public class SalesPoint {
    private String name, addresse, items, description;

    public SalesPoint(String name, String addresse, String items, String description) {
        this.name = name;
        this.addresse = addresse;
        this.items = items;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
