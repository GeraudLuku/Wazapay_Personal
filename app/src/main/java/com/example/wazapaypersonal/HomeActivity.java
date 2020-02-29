package com.example.wazapaypersonal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    private View mHomeScreen, mAboutScreen;  // main pages on the home screen . . .


    private TextView mAboutLabel; // title variable for the about page

    private ViewPager mHomeViewPager; //viewpager variable
    private TabLayout tabs; //tabs variable

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomeScreen = findViewById(R.id.homePage);
        mAboutScreen = findViewById(R.id.homeAbout);


        mAboutLabel = findViewById(R.id.home_about_title);
        mHomeViewPager = findViewById(R.id.hm_viewPager);
        mHomeViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        tabs = findViewById(R.id.hm_tabs);
        tabs.setupWithViewPager(mHomeViewPager);

        autoSwipeViews();

    }

    @Override
    public void onBackPressed() {
        if (mAboutScreen.getVisibility() == View.VISIBLE) {
            onHomeBackClickedListener(null);
        } else {
            super.onBackPressed();
        }
    }

    //listener takes user to wazahub website onclick
    public void onMoreInfoClickedListener(View view) {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wazapay.com"));
        startActivity(browse);
    }


    //method to hide the about popup for each service
    public void onHomeBackClickedListener(View view) {
        mAboutScreen.setVisibility(View.GONE);
    }

    /***
     * The dashboard listener uses a viewgroup to sieth through the various dashboard items and
     * find the clicked item by reading the text on the card.
     *
     * May be subject to change if languages are swapped. In which case check the possibility of comparing dr
     * **/
    public void onDashItemClickedListener(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.hm_1:
                mAboutLabel.setText("Deposit");
                break;
            case R.id.hm_2:
                mAboutLabel.setText("withdrawals");
                break;
            case R.id.hm_3:
                mAboutLabel.setText("direct transfer");
                break;
            case R.id.hm_4:
                mAboutLabel.setText("money request");
                break;
            case R.id.hm_5:
                mAboutLabel.setText("escrow transfer");
                break;
            case R.id.hm_6:
                mAboutLabel.setText("bulk payments");
                break;
            case R.id.hm_7:
                mAboutLabel.setText("recurring payments");
                break;
            case R.id.hm_8:
                mAboutLabel.setText("invoicing");
                break;
            case R.id.hm_9:
                mAboutLabel.setText("currency exchange");
                break;
            case R.id.hm_10:
                mAboutLabel.setText("utility bills");
                break;
            case R.id.hm_11:
                mAboutLabel.setText("njangi");
                break;
            case R.id.hm_12:
                mAboutLabel.setText("piggy wallets");
                break;
            case R.id.hm_13:
                mAboutLabel.setText("cash-flow agents");
                break;
            case R.id.hm_14:
                mAboutLabel.setText("donations");
                break;
            case R.id.hm_15:
                mAboutLabel.setText("referral system");
                break;
        }

        mAboutScreen.setVisibility(View.VISIBLE);
    }

    //view pager left navigator
    //used to move the view pager one page back
    public void onHomeLeft(View view) {
        int page = mHomeViewPager.getCurrentItem();
        if (page != 0)
            mHomeViewPager.setCurrentItem(page - 1, false);
    }

    //view pager right navigator.
    //used to right step the pages on the viewpager
    public void onRightClicked(View view) {
        int page = mHomeViewPager.getCurrentItem();
        if (page != 5)
            mHomeViewPager.setCurrentItem(page + 1, false);
    }

    //swipe viewpager automatically after ten seconds
    public void autoSwipeViews() {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new SliderTimer(), 10000, 10000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //destroy timer when exiting the application
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /***
     * Onclick listener for sign up button
     ****/
    public void onSignupClickedListener(View view) {

        Intent intent = new Intent("net.wazapay.registration.RegisterActivity");
        startActivity(intent);
    }

    public void onLoginClickedListener(View view) {
        // TODO : handle the login click event
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onLearnMoreClickedListener(View view) {

        // TODO : handle the clicks on the learn more button in the pop up
        Toast.makeText(this, "Go to service page on the website . . . ", Toast.LENGTH_SHORT).show();
    }

    /***
     * Sections pager adapter to manage views at the top of the home page
     * ***/
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return Serve.newInstance(i);
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

    /***
     * Fragment to hold the different service text
     * Uses home_swipe_text_item to render the localized content
     ***/

    public static class Serve extends Fragment {

        private static final String SECTION_NUMBER = "Section_Number";
        //Root view
        private View root;

        //content host
        private TextView textContent;


        public static Serve newInstance(int position) {
            Serve serve = new Serve();
            Bundle bundle = new Bundle();
            bundle.putInt(SECTION_NUMBER, position);
            serve.setArguments(bundle);
            return serve;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            root = inflater.inflate(R.layout.home_swip_text_item, container, false);

            textContent = root.findViewById(R.id.home_swip_textView);

            switch (getArguments().getInt(SECTION_NUMBER)) {
                case 0:
                    textContent.setText("Position zero");
                    break;
                case 1:
                    textContent.setText("Position one");
                    break;
                case 2:
                    textContent.setText("Position two");
                    break;
                case 3:
                    textContent.setText("Position three");
                    break;
                case 4:
                    textContent.setText("Position four");
                    break;
                case 5:
                    textContent.setText("Position five");
                    break;
            }

            return root;
        }
    }

    /***
     * Timer manager. . .
     * helps swipe services automatically
     * ***/
    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int page = mHomeViewPager.getCurrentItem();

                    if (page == 5) {
                        mHomeViewPager.setCurrentItem(0, true);
                    } else {
                        mHomeViewPager.setCurrentItem(page + 1, true);
                    }
                }
            });
        }
    }

}
