package com.example.verification_module;

import android.content.Context;
import android.content.SharedPreferences;

public class VerificationPreferences {


    public static Context verificationContext;

    public static SharedPreferences sharedPreferences;


    public static String emailVerificationStateFlag = "EmailVerificationStateFlag";
    public static String phoneVerificationStateFlag = "PhoneNumberVerificationStateFlag";
    public static String identVerificationStateFlag = "IdentityVerificationStateFlag";
    public static String addVerificationStateFlag = "AddressVerificationStateFlag";
    public static String payVerificationStateFlag = "PaymentVerificationStateFlag";

    public VerificationPreferences(Context context) {
        this.verificationContext = context;
    }

    public static int getEmailVerificationStateFlag() {

        sharedPreferences = verificationContext.getSharedPreferences(emailVerificationStateFlag, Context.MODE_PRIVATE);


        int flag = sharedPreferences.getInt(emailVerificationStateFlag,0);

        return flag;
    }

    public static void setEmailVerificationStateFlag(int Flag) {
        sharedPreferences = verificationContext.getSharedPreferences(emailVerificationStateFlag,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(emailVerificationStateFlag,Flag);

        e.apply();
    }

    public static int getPhoneVerificationStateFlag() {
        sharedPreferences = verificationContext.getSharedPreferences(phoneVerificationStateFlag, Context.MODE_PRIVATE);


        int flag = sharedPreferences.getInt(phoneVerificationStateFlag,0);

        return flag;
    }

    public static void setPhoneVerificationStateFlag(int Flag) {
        sharedPreferences = verificationContext.getSharedPreferences(phoneVerificationStateFlag,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(phoneVerificationStateFlag,Flag);

        e.apply();
    }

    public static int getIdentVerificationStateFlag() {
        sharedPreferences = verificationContext.getSharedPreferences(identVerificationStateFlag, Context.MODE_PRIVATE);


        int flag = sharedPreferences.getInt(identVerificationStateFlag,0);

        return flag;
    }

    public static void setIdentVerificationStateFlag(int Flag) {
        sharedPreferences = verificationContext.getSharedPreferences(identVerificationStateFlag,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(identVerificationStateFlag,Flag);

        e.apply();
    }

    public static int getAddVerificationStateFlag() {
        sharedPreferences = verificationContext.getSharedPreferences(addVerificationStateFlag, Context.MODE_PRIVATE);


        int flag = sharedPreferences.getInt(addVerificationStateFlag,0);

        return flag;
    }

    public static void setAddVerificationStateFlag(int Flag) {
        sharedPreferences = verificationContext.getSharedPreferences(addVerificationStateFlag, Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(addVerificationStateFlag,Flag);

        e.apply();
    }

    public static int getPayVerificationStateFlag() {
        sharedPreferences = verificationContext.getSharedPreferences(payVerificationStateFlag, Context.MODE_PRIVATE);


        int flag = sharedPreferences.getInt(payVerificationStateFlag,0);

        return flag;
    }

    public static void setPayVerificationStateFlag(int Flag) {
        sharedPreferences = verificationContext.getSharedPreferences(payVerificationStateFlag, Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(payVerificationStateFlag,Flag);

        e.apply();
    }
}
