package com.example.wazapaypersonal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wazapaypersonal.Registration.PersonalAccountActivity;
import com.example.wazapaypersonal.Verification.VerificationActivity;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onLogin(View view) {
        //startActivity(new Intent("net.wazapay.verification.AccountVerification"));
        startActivity(new Intent(this, VerificationActivity.class));
    }

    public void goBack(View view) {
        //startActivity(new Intent("net.wazapay.verification.AccountVerification"));
        onBackPressed();
    }

    public void viewForgotPassword(View view) {
        startActivity(new Intent(this, ForgotPassword.class));
    }

    public void onSignUp(View view) {
        startActivity(new Intent(this, PersonalAccountActivity.class));

        //TODO: RegisterActivity is an On-Demand Dynamic Feature handle the download and installation here!
//        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
//        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

}
