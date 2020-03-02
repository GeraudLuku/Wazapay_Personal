package com.example.wazapaypersonal.Agent_Listing.Feature;


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


import com.example.wazapaypersonal.Agent_Listing.Adapter.AgentListRecyclerAdapter;
import com.example.wazapaypersonal.Agent_Listing.Model.Agent;
import com.example.wazapaypersonal.Agent_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {

    private AgentListRecyclerAdapter mAgentListRecyclerAdapter;

    private ViewModelClass mViewModelClass;

    private ArrayList<Agent> mAgentList = new ArrayList<>();

    public ListViewFragment() {
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
                mAgentListRecyclerAdapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);


        //recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        //contacts recycler view settings
        mAgentListRecyclerAdapter = new AgentListRecyclerAdapter(view.getContext(), mAgentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAgentListRecyclerAdapter);

        //load dummy data
        prepareData();


        return view;
    }

    private void prepareData() {
        //initialize contacts array
        //create dummy favourite contacts
        mAgentList.add(new Agent("Fon Ndikum", "1,000,000 CFA", 4.155036, 9.290329));
        mAgentList.add(new Agent("Luku Geraud", "900,000 CFA", 4.154405, 9.290087));
        mAgentList.add(new Agent("Frankie", "20,000 CFA", 4.154068, 9.290661));
        mAgentList.add(new Agent("Godlove Fonzeyuy", "40,000,000 CFA", 4.154448, 9.291541));
        mAgentList.add(new Agent("Louraine Pink", "1,000 CFA", 4.154667, 9.290774));


        mAgentListRecyclerAdapter.notifyDataSetChanged();
    }

}
