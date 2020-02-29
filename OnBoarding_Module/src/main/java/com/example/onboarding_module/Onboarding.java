package com.example.onboarding_module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wazapaypersonal.HomeActivity;
import com.google.android.material.tabs.TabLayout;

public class Onboarding extends AppCompatActivity {

    private static TabLayout mBoardingTabs;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding_main);

        //get views  . . .
        mBoardingTabs = findViewById(R.id.onBoardingTabs);
        mViewPager = findViewById(R.id.onBoardingViewPager);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mBoardingTabs.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(0, true);

    }

    public void onBoardingSkipClicked(View view) {
        //goto home activity
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }


    public void onBoardingNextClicked(View view) {
        int current = mViewPager.getCurrentItem();
        if (current != 5)
            mViewPager.setCurrentItem(current + 1);
        else {
            //goto home activity
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }


    /*
     * Sections Pager adapter for managing the different pages of the onBoarding screen
     *
     * */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final Context context;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return Pages.newInstance(position);
        }


        @Override
        public int getCount() {
            return 6;
        }


    }


    /*
     *
     * Fragment for hoisting the different view
     * */
    public static class Pages extends Fragment {

        private static final String SECTION_NUMBER = "Section_Number"; //variable that holds page
        private View view; // variable that holds root view of the view pager fragment

        private TextView description; // view holds text at bottom of the page describing the service
        private TextView label;  // view holds text about a service


        private ImageView onBoardingTabImageView;


        public static Pages newInstance(int position) {
            Pages pages = new Pages();
            Bundle bun = new Bundle();
            bun.putInt(SECTION_NUMBER, position);
            pages.setArguments(bun);
            return pages;
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            //get hold of the rootview
            view = inflater.inflate(R.layout.on_boarding_fragment, container, false);

            description = view.findViewById(R.id.onBoardingTabDescriptiveTextView);
            label = view.findViewById(R.id.onBoardingTabTitleTextView);

            onBoardingTabImageView = view.findViewById(R.id.onBoardingTabImageView);

            //change text and images on the onBoarding page
            switch (getArguments().getInt(SECTION_NUMBER)) {

                //these strings should be replaced with localized strings from the server !!!!

                case 0:
                    label.setText("Money Transfer");
                    onBoardingTabImageView.setImageDrawable(view.getContext().
                            getResources().getDrawable(R.drawable.money_transfer2));
                    break;

                case 1:
                    label.setText("Online Shopping");
                    onBoardingTabImageView.setImageDrawable(view.getContext().
                            getResources().getDrawable(R.drawable.online_shipping));
                    break;

                case 2:
                    label.setText("Checkout Process");
                    onBoardingTabImageView.setImageDrawable(view.getContext().
                            getResources().getDrawable(R.drawable.money_transactions));
                    break;

                case 3:
                    label.setText("Cashless Payment");
                    onBoardingTabImageView.setImageDrawable(view.getContext().
                            getResources().getDrawable(R.drawable.checkout_process));
                    break;

                case 4:
                    label.setText("CashIn / CashOut");
                    onBoardingTabImageView.setImageDrawable(view.getContext().
                            getResources().getDrawable(R.drawable.instant_payments));
                    break;

                case 5:
                    label.setText("CashFlow Agents");
                    onBoardingTabImageView.setImageDrawable(view.getContext().
                            getResources().getDrawable(R.drawable.local_cashiers));
                    break;
            }


            return view;
        }

    }
}

