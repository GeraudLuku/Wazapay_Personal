package com.example.wazapaypersonal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //go to onboarding activity
        Intent intent = new Intent("net.wazapay.onboarding.OnBoarding");
        startActivity(intent);
        finish();
    }
}
