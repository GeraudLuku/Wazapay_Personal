package com.example.wazapaypersonal.Contacts.Features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.wazapaypersonal.R;

import es.dmoral.toasty.Toasty;


public class AddableAsContactFragment extends Fragment {

    public AddableAsContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addable_as_contact, container, false);
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        //info button
        ImageButton infoButton = view.findViewById(R.id.info_button);
        infoButton.setOnClickListener(mOnClickListener);

        //save button
        Button saveButton = view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(mOnClickListener);

        return view;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.info_button:
                    //show dialog to display custom message
                    new AlertDialog.Builder(v.getContext())
                            .setMessage("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled " +
                                    "it to make a type specimen book. " +
                                    "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.")
                            .setIcon(R.drawable.ic_info)
                            .show();
                    break;
                case R.id.save_button:
                    Toasty.success(v.getContext(), "Contact settings saved", 1).show();
                    Navigation.findNavController(getView()).popBackStack();
                    break;
            }
        }
    };


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.contacts_settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Navigation.findNavController(getView()).popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
