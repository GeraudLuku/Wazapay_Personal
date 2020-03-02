package com.example.wazapaypersonal.Dashboard.Model;

public class WalletItem {
    private Integer id;
    private Integer balance;
    private String currency;
    private String country;

    public WalletItem(Integer id, Integer balance, String currency, String country) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
