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

import com.example.wazapaypersonal.Merchant_Listing.Adapters.PointOfSalesRecyclerAdapter;
import com.example.wazapaypersonal.Merchant_Listing.Models.SalesPoint;
import com.example.wazapaypersonal.Merchant_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PointOfSalesFragment extends Fragment {

    private PointOfSalesRecyclerAdapter mPointOfSalesRecyclerAdapter;

    private ViewModelClass mViewModelClass;

    private ArrayList<SalesPoint> mSalesPoint = new ArrayList<>();

    public PointOfSalesFragment() {
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
                mPointOfSalesRecyclerAdapter.getFilter().filter(s);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_point_of_sales, container, false);

        //recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        //contacts recycler view settings
        mPointOfSalesRecyclerAdapter = new PointOfSalesRecyclerAdapter(view.getContext(), mSalesPoint);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mPointOfSalesRecyclerAdapter);

        //load dummy data
        prepareData();

        return view;
    }

    private void prepareData() {
        //initialize contacts array
        //create dummy favourite contacts
        mSalesPoint.add(new SalesPoint("Super U", "Malingo-street,  Buea, Cameroon", "Clothes, Shoes , Perfumes, Cutlery, Make-up","lorem lorem lorem lorem lorem lorem"));
        mSalesPoint.add(new SalesPoint("Mahima", "Bonamussadi, Denver, Douala", "Food, divertisement, plessir","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));
        mSalesPoint.add(new SalesPoint("Le Mediterrane", "Bonamussadi, Denver, Douala", "Food, divertisement, plessir","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));
        mSalesPoint.add(new SalesPoint("Terific Coffee", "Bonamussadi, Denver, Douala", "Food, divertisement, plessir","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));
        mSalesPoint.add(new SalesPoint("Santa Lucia", "Bonamussadi, Denver, Douala", "Food, divertisement, plessir","Whether you're new to Android and need some fresh, new games to start building out your Google Play library or simply looking for the latest trendy games that are worthy of your time and attention, these are the best Android games you can find right now."));

        mPointOfSalesRecyclerAdapter.notifyDataSetChanged();
    }

}
