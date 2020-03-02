package com.example.wazapaypersonal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wazapaypersonal.Registration.PersonalAccountActivity;

public class ResetPassword extends AppCompatActivity {

    private View mResetPass1, mResetPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mResetPass1 = findViewById(R.id.reset_password_1);
        mResetPass2 = findViewById(R.id.reset_password_2);
    }


    public void onReset(View view) {
        //show the second view make it visible
        mResetPass1.setVisibility(View.GONE);
        mResetPass2.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {

        //if back is pressed when reset password 2 view is visibile, make it gone aand set the reset password view 1 visible else
        if (mResetPass2.getVisibility() == View.VISIBLE) {
            mResetPass1.setVisibility(View.VISIBLE);
            mResetPass2.setVisibility(View.GONE);
        } else
            super.onBackPressed();
    }

    public void onSignUp(View view) {
        startActivity(new Intent(this, PersonalAccountActivity.class));
        finish();

        //TODO: RegisterActivity is an On-Demand Dynamic Feature handle the download and installation here!
//        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
//        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    public void goBack(View view) {
        //startActivity(new Intent("net.wazapay.verification.AccountVerification"));
        onBackPressed();
    }

    public void onLogin(View view) {
        //startActivity(new Intent("net.wazapay.verification.AccountVerification"));
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
