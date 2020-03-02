package com.example.wazapaypersonal.Contacts.Features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.wazapaypersonal.R;

import es.dmoral.toasty.Toasty;


public class ContactsSettingsFragment extends Fragment {

    public ContactsSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts_settings, container, false);
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set onclick listener for listview
        ListView listView = view.findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_addableAsContactFragment);
                        break;
                    case 1:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_blockContactsFragment);
                        break;
                    case 2:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_deleteContactsFragment);
                        break;
                    case 3:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_reportContactsFragment);
                        break;
                    case 4:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_exportContactsFragment);
                        break;
                    case 5:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_importContactsFragment);
                        break;
                    case 6:
                        Navigation.findNavController(view).navigate(R.id.action_contactsSettingsFragment_to_shareContactsFragment);
                        break;
                    default:
                        Toasty.error(getContext(), "Nothing selected").show();
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.contacts_settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.findNavController(getView()).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
