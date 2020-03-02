package com.example.merchantlisting_module;


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

import com.example.merchantlisting_module.Adapters.MobileAppRecyclerAdapter;
import com.example.merchantlisting_module.Models.Mobile;
import com.example.merchantlisting_module.ViewModel.ViewModelClass;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MobileAppFragment extends Fragment {

    private MobileAppRecyclerAdapter mMobileAppRecyclerAdapter;

    private ViewModelClass mViewModelClass;

    private ArrayList<Mobile> mMobileList = new ArrayList<>();

    public MobileAppFragment() {
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
                mMobileAppRecyclerAdapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mobile_app, container, false);

        //recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        //contacts recycler view settings
        mMobileAppRecyclerAdapter = new MobileAppRecyclerAdapter(view.getContext(), mMobileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMobileAppRecyclerAdapter);

        //load dummy data
        prepareData();


        return view;
    }

    private void prepareData() {
        //initialize contacts array
        //create dummy favourite contacts
        mMobileList.add(new Mobile("Call of Duty Mobile", "www.codmobile.com", "www.codMobile.com","lorem lorem lorem lorem lorem lorem"));
        mMobileList.add(new Mobile("PUBG Mobile", "www.pubg.com/mobile", "www.pubg.com","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));
        mMobileList.add(new Mobile("Whatsapp", "www.whatsapbyfacebook.com", "www.whatsapp.com","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));
        mMobileList.add(new Mobile("Facebook Messenger", "www.fb.com", "www.facebook.com","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));
        mMobileList.add(new Mobile("Flappy Birds", "flappybirds.com", "www.flappyMe.com","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));

        mMobileAppRecyclerAdapter.notifyDataSetChanged();
    }

}
