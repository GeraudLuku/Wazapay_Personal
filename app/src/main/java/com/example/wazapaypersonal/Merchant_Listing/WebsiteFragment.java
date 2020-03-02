package com.example.wazapaypersonal.Merchant_Listing;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wazapaypersonal.Merchant_Listing.Adapters.WebsiteRecyclerAdapter;
import com.example.wazapaypersonal.Merchant_Listing.Models.Website;
import com.example.wazapaypersonal.Merchant_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebsiteFragment extends Fragment {

    private WebsiteRecyclerAdapter mWebsiteRecyclerAdapter;

    private ViewModelClass mViewModelClass;

    private ArrayList<Website> mWebsiteList = new ArrayList<>();

    public WebsiteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModelClass = new ViewModelProvider(getActivity()).get(ViewModelClass.class);
        //observe selected contacts object
        mViewModelClass.getSearchKey().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //get search filter
                mWebsiteRecyclerAdapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_website, container, false);

        //recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        //contacts recycler view settings
        mWebsiteRecyclerAdapter = new WebsiteRecyclerAdapter(view.getContext(), mWebsiteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mWebsiteRecyclerAdapter);

        //load dummy data
        prepareData();

        return view;
    }

    private void prepareData() {
        //initialize contacts array
        //create dummy favourite contacts
        mWebsiteList.add(new Website("Google", "www.google.com", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));
        mWebsiteList.add(new Website("Wikipedia", "www.wikipedia.com/fr", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));
        mWebsiteList.add(new Website("Gmail", "mail.google.com", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));
        mWebsiteList.add(new Website("Yahoo", "www.yahoo.cm/en", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));
        mWebsiteList.add(new Website("Firebase", "firebase.google.com", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));


        mWebsiteRecyclerAdapter.notifyDataSetChanged();
    }

}
