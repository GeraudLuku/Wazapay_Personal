package com.example.personaldashboard_module;

import android.content.Context;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;

public class CountryToPhonePrefix {

    private final Context context;
    private PhoneNumberUtil phoneNumberUtil;

    public CountryToPhonePrefix(Context context) {
        this.context = context;
        phoneNumberUtil = PhoneNumberUtil.createInstance(context);
    }

     public String getCountryIsoCode(String number) {

        String validatedNumber = (number.startsWith("+")) ? number : "+" + number;
        Phonenumber.PhoneNumber phoneNumber = null;

        try {
            phoneNumber = phoneNumberUtil.parse(validatedNumber, null);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }

        if (phoneNumber == null) return null;

        return phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode());
    }
}
