package com.example.wazapaypersonal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wazapaypersonal.Onboarding.Onboarding;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //go to onboarding activity
        Intent intent = new Intent(this, Onboarding.class);
        startActivity(intent);
        finish();
    }
}
