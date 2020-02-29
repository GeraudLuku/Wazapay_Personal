package com.example.personaldashboard_module.Model;

public class Wallet {
    String walletCurrencyOne;
    int walletAmountOne;
    String walletCountryOne;

    String walletCurrencyTwo;
    int walletAmountTwo;
    String walletCountryTwo;

    String walletCurrencyThree;
    int walletAmountThree;
    String walletCountryThree;

    public Wallet(

            String walletCurrencyOne,
            int walletAmountOne,
            String walletCountryOne,
            String walletCurrencyTwo,
            int walletAmountTwo,
            String walletCountryTwo,
            String walletCurrencyThree,
            int walletAmountThree,
            String walletCountryThree
    ) {

        this.walletCurrencyOne = walletCurrencyOne;
        this.walletAmountOne = walletAmountOne;
        this.walletCountryOne = walletCountryOne;
        this.walletCurrencyTwo = walletCurrencyTwo;
        this.walletAmountTwo = walletAmountTwo;
        this.walletCountryTwo = walletCountryTwo;
        this.walletCurrencyThree = walletCurrencyThree;
        this.walletAmountThree = walletAmountThree;
        this.walletCountryThree = walletCountryThree;
    }

    public String getWalletCurrencyOne() {
        return walletCurrencyOne;
    }

    public void setWalletCurrencyOne(String walletCurrencyOne) {
        this.walletCurrencyOne = walletCurrencyOne;
    }

    public int getWalletAmountOne() {
        return walletAmountOne;
    }

    public void setWalletAmountOne(int walletAmountOne) {
        this.walletAmountOne = walletAmountOne;
    }

    public String getWalletCountryOne() {
        return walletCountryOne;
    }

    public void setWalletCountryOne(String walletCountryOne) {
        this.walletCountryOne = walletCountryOne;
    }

    public String getWalletCurrencyTwo() {
        return walletCurrencyTwo;
    }

    public void setWalletCurrencyTwo(String walletCurrencyTwo) {
        this.walletCurrencyTwo = walletCurrencyTwo;
    }

    public int getWalletAmountTwo() {
        return walletAmountTwo;
    }

    public void setWalletAmountTwo(int walletAmountTwo) {
        this.walletAmountTwo = walletAmountTwo;
    }

    public String getWalletCountryTwo() {
        return walletCountryTwo;
    }

    public void setWalletCountryTwo(String walletCountryTwo) {
        this.walletCountryTwo = walletCountryTwo;
    }

    public String getWalletCurrencyThree() {
        return walletCurrencyThree;
    }

    public void setWalletCurrencyThree(String walletCurrencyThree) {
        this.walletCurrencyThree = walletCurrencyThree;
    }

    public int getWalletAmountThree() {
        return walletAmountThree;
    }

    public void setWalletAmountThree(int walletAmountThree) {
        this.walletAmountThree = walletAmountThree;
    }

    public String getWalletCountryThree() {
        return walletCountryThree;
    }

    public void setWalletCountryThree(String walletCountryThree) {
        this.walletCountryThree = walletCountryThree;
    }
}
