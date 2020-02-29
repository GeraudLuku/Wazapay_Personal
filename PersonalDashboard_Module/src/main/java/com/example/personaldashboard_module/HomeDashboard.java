package com.example.personaldashboard_module;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;


public class HomeDashboard extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView child;

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_dashboard);

        changeStatusBarColor();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar.getBackground().setAlpha(0);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_wallet, R.id.nav_transaction, R.id.nav_contacts, R.id.nav_payment_methods, R.id.nav_verifications,
                R.id.nav_referral, R.id.nav_agent, R.id.nav_merchant, R.id.nav_report, R.id.nav_review, R.id.menu_transaction, R.id.menu_notification, R.id.personalProfileFragment,
                R.id.menu_wallet)
                .setDrawerLayout(mDrawerLayout)
                .build();

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        setupWithNavController(navigationView, mNavController);
        setupWithNavController(bottomNavigationView, mNavController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_personal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onCreateNewWallet(View view) {
        Toast.makeText(this, "TODO: Create New Wallet", Toast.LENGTH_LONG).show();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
