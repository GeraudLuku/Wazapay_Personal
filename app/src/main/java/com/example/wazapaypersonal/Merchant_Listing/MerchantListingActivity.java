package com.example.wazapaypersonal.Merchant_Listing;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.wazapaypersonal.Merchant_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;
import com.google.android.material.tabs.TabLayout;

public class MerchantListingActivity extends AppCompatActivity {

    private ViewModelClass mViewModelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_activity_main);

        //init view model
        mViewModelClass = new ViewModelProvider(this).get(ViewModelClass.class);

        //init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set up view pager
        final ViewPager viewPager = findViewById(R.id.viewPager);

        // set tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.parseColor("#797979"), Color.parseColor("#333333")); //inactive color to active color

        viewPager.setAdapter(new MerchantListAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //for view page adapter
    public class MerchantListAdapter extends FragmentPagerAdapter {

        private MerchantListAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new WebsiteFragment();
                case 1:
                    return new MobileAppFragment();
                case 2:
                    return new PointOfSalesFragment();
                case 3:
                    return new TaxisFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Website";
                case 1:
                    return "Mobile Apps";
                case 2:
                    return "Point of Sales";
                case 3:
                    return "Taxis";
                default:
                    return "Object";
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.merchant_menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);

        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MerchantListingActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                //send search text to child fragment
                mViewModelClass.setSearchKey(query.toString());
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //send search text to child fragment
                mViewModelClass.setSearchKey(newText.toString());
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
