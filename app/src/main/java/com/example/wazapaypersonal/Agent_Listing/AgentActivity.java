package com.example.wazapaypersonal.Agent_Listing;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.wazapaypersonal.Agent_Listing.Feature.ListViewFragment;
import com.example.wazapaypersonal.Agent_Listing.Feature.MapViewFragment;
import com.example.wazapaypersonal.Agent_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;
import com.google.android.material.tabs.TabLayout;

public class AgentActivity extends AppCompatActivity {

    private ViewModelClass mViewModelClass;
    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_activity);

        //init view model
        mViewModelClass = new ViewModelProvider(this).get(ViewModelClass.class);

        //init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mSearchBar = findViewById(R.id.search_bar);
        mSearchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_SEARCH) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                    // code to hide the soft keyboard
                    mInputMethodManager.hideSoftInputFromWindow(mSearchBar.getApplicationWindowToken(), 0);
                    return true;
                } else {
                    return false;
                }
            }
        });
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //send search text to child fragment
                mViewModelClass.setSearchKey(s.toString());
            }
        });

        //set up view pager
        final ViewPager viewPager = findViewById(R.id.viewPager);

        // set tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.parseColor("#797979"), Color.parseColor("#333333")); //inactive color to active color

        viewPager.setAdapter(new AgentListingPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
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
    public class AgentListingPageAdapter extends FragmentPagerAdapter {

        private AgentListingPageAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ListViewFragment();
                case 1:
                    return new MapViewFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "List View";
                case 1:
                    return "Map View";
                default:
                    return "Object";
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.merchant_menu, menu);
        return true;
    }

    private EditText mSearchBar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                //show search bar
                if (mSearchBar.getVisibility() == View.VISIBLE) {
                    mSearchBar.setVisibility(View.GONE);
                    // code to hide the soft keyboard
                    mInputMethodManager.hideSoftInputFromWindow(mSearchBar.getApplicationWindowToken(), 0);
                } else {
                    mSearchBar.setVisibility(View.VISIBLE);
                    requestSearchBarFocus();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestSearchBarFocus() {
        mSearchBar.setFocusableInTouchMode(true);
        mSearchBar.setFocusable(true);
        mSearchBar.requestFocus();
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
