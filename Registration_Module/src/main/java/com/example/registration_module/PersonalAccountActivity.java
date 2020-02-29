package com.example.registration_module;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalAccountActivity extends AppCompatActivity {

    private View mNextRegistrationView, mFinalRegistrationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);

        //configure toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0A845F")));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNextRegistrationView = findViewById(R.id.layout_registration_personal_next);
        mFinalRegistrationView = findViewById(R.id.layout_registration_personal_finish);

    }

    public void validateBeforeNext() {
        mNextRegistrationView.setVisibility(View.GONE);
        mFinalRegistrationView.setVisibility(View.VISIBLE);
    }

    public void validateBeforeFinish() {

    }

    public void onClickNextOnAgentAccountRegistration(View view) {
        validateBeforeNext();
    }

    public void onClickFinishOnPersonalAccount(View view) {

        validateBeforeFinish();
        startActivity(new Intent("net.wazapay.personaldashboard.PersonalDashboard"));
    }

    @Override
    public void onBackPressed() {

        if (mFinalRegistrationView.getVisibility() == View.VISIBLE) {
            mFinalRegistrationView.setVisibility(View.GONE);
            mNextRegistrationView.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mFinalRegistrationView.getVisibility() == View.VISIBLE) {
                    mFinalRegistrationView.setVisibility(View.GONE);
                    mNextRegistrationView.setVisibility(View.VISIBLE);
                } else {
                    super.onBackPressed();
                }
                break;
        }

        return true;
    }
}
