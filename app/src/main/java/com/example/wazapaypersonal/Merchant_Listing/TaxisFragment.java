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


import com.example.wazapaypersonal.Merchant_Listing.Adapters.TaxisRecyclerAdapter;
import com.example.wazapaypersonal.Merchant_Listing.Models.Taxi;
import com.example.wazapaypersonal.Merchant_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaxisFragment extends Fragment {

    private TaxisRecyclerAdapter mTaxisRecyclerAdapter;

    private ViewModelClass mViewModelClass;

    private ArrayList<Taxi> mTaxiList = new ArrayList<>();

    public TaxisFragment() {
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
                mTaxisRecyclerAdapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taxis, container, false);

        //recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        //contacts recycler view settings
        mTaxisRecyclerAdapter = new TaxisRecyclerAdapter(view.getContext(), mTaxiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mTaxisRecyclerAdapter);

        //load dummy data
        prepareData();

        return view;
    }

    private void prepareData() {
        //initialize contacts array
        //create dummy favourite contacts
        mTaxiList.add(new Taxi("Luku Geraud", "+237 6 78 45 12 36", "Malingo-street,  Buea, Cameroon"));
        mTaxiList.add(new Taxi("Harold", "+237 6 78 45 12 36", "Bonamussadi, Denver, Douala"));
        mTaxiList.add(new Taxi("Bier Bier", "+237 6 78 45 12 36", "Bonamussadi, Denver, Douala"));
        mTaxiList.add(new Taxi("Josua", "+237 6 78 45 12 36", "Bonamussadi, Denver, Douala"));
        mTaxiList.add(new Taxi("Brice Farnesi", "+237 6 78 45 12 36", "Bonamussadi, Denver, Douala"));

        mTaxisRecyclerAdapter.notifyDataSetChanged();
    }

}
