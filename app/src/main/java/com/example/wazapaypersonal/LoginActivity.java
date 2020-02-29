package com.example.wazapaypersonal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onLogin(View view) {
        //startActivity(new Intent("net.wazapay.verification.AccountVerification"));
        startActivity(new Intent("net.wazapay.verification.MainVerificationActivity"));
    }

    public void goBack(View view) {
        //startActivity(new Intent("net.wazapay.verification.AccountVerification"));
        onBackPressed();
    }

    public void viewForgotPassword(View view) {
        startActivity(new Intent(this, ForgotPassword.class));
    }

    public void onSignUp(View view) {
        startActivity(new Intent("net.wazapay.registration.RegisterActivity"));

        //TODO: RegisterActivity is an On-Demand Dynamic Feature handle the download and installation here!
//        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
//        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    public void onClickChangeLocal(View view) {
        Intent intent = new Intent("net.wazapay.onboarding.PersonalDashboard");
        startActivity(intent);
    }
}
