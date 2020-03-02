package com.example.wazapaypersonal.Contacts.Features;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wazapaypersonal.Contacts.Adapters.ContactsRecyclerAdapter;
import com.example.wazapaypersonal.Contacts.Adapters.FavouriteContactsRecyclerAdapter;
import com.example.wazapaypersonal.Contacts.Interfaces.ContactsRecyclerInterface;
import com.example.wazapaypersonal.Contacts.Interfaces.FavoriteContactsRecyclerInterface;
import com.example.wazapaypersonal.Contacts.Models.Contact;
import com.example.wazapaypersonal.Contacts.Models.RetroPhoto;
import com.example.wazapaypersonal.Contacts.ViewModel.ContactsViewModel;
import com.example.wazapaypersonal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainFragment extends Fragment implements FavoriteContactsRecyclerInterface, ContactsRecyclerInterface {

    private ArrayList<Contact> mContactsList = new ArrayList<>();
    private RecyclerView mContactsRecyclerView;
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;

    private ArrayList<Contact> mFavouriteContactsList = new ArrayList<>();
    private RecyclerView mFavouriteContactsRecyclerView;
    private FavouriteContactsRecyclerAdapter mFavouriteContactsRecyclerAdapter;

    private ContactsViewModel mContactsViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    //init retrofit when the activity view was sucessfully created
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get data from view model class here
        mContactsViewModel = new ViewModelProvider(getActivity()).get(ContactsViewModel.class);
        mContactsViewModel.init(); //always init in case its null
        //observe data object
        mContactsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<RetroPhoto>>() {
            @Override
            public void onChanged(List<RetroPhoto> retroPhotos) {
                Log.v("RETROFIT", Locale.getDefault().getLanguage());
                if (retroPhotos != null) { //else it will crash if there is no internel because it will  return a null object
                    Log.v("RETROFIT", retroPhotos.get(0).getTitle());
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        mContactsRecyclerView = view.findViewById(R.id.contacts);
        mFavouriteContactsRecyclerView = view.findViewById(R.id.favorite_contacts);

        //favourite contacts recycler adapter settings
        mFavouriteContactsRecyclerAdapter = new FavouriteContactsRecyclerAdapter(view.getContext(), mFavouriteContactsList, this);
        mFavouriteContactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFavouriteContactsRecyclerView.setAdapter(mFavouriteContactsRecyclerAdapter);

        //contacts recycler view settings
        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(view.getContext(), mContactsList, this);
        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mContactsRecyclerView.setAdapter(mContactsRecyclerAdapter);


        //get floating action button
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(mOnclickListener);

        //create contacts layout
        LinearLayout createContact = view.findViewById(R.id.create_contact);
        createContact.setOnClickListener(mOnclickListener);

        //load dummy data
        if (mContactsList.isEmpty())
            loadContacts();

        return view;
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.floatingActionButton:
                    Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_addContactsFragment);
                    break;
                case R.id.create_contact:
                    Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_addContactsFragment);
                    break;
            }
        }
    };

    private void loadContacts() {

        //create dummy favourite contacts
        mFavouriteContactsList.add(new Contact("Fon Ndikum", "Ndikum@bassnectar.com", "223444000888", "https://picsum.photos/id/237/400"));
        mFavouriteContactsList.add(new Contact("Godlove Fonzenyuy", "Fonzenyuy@gmail.com", "223444000888", "https://picsum.photos/id/1001/400"));

        //create dummy normal contacts
        mContactsList.add(new Contact("Geraud", "lukugeraud@yahoo.com", "237674753811", "https://picsum.photos/id/1002/400"));
        mContactsList.add(new Contact("Frankie", "frankieflash@yahoo.com", "237677333222", "https://picsum.photos/id/1003/400"));
        mContactsList.add(new Contact("Esther", "Estherfree@gmail.com", "222666444111", "https://picsum.photos/id/1040/400"));
        mContactsList.add(new Contact("Augustine", "flowersbloom@outlook.com", "223444000888", "https://picsum.photos/id/1054/400"));
        mContactsList.add(new Contact("Janiver", "Janiver@outlook.com", "223444000888", "https://picsum.photos/id/1066/400"));
        mContactsList.add(new Contact("Nganju Christopher", "Christopher@gmail.com", "223444000888", "https://picsum.photos/id/10/400"));
        mContactsList.add(new Contact("Nkaime Lovelyne", "Lovelyne@gmail.com", "223444000888", "https://picsum.photos/id/11/400"));

        mContactsRecyclerAdapter.notifyDataSetChanged();
        mFavouriteContactsRecyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onFavouriteContactCheckListener(final Contact contact, final int position) {

        android.os.Handler mHandler = getActivity().getWindow().getDecorView().getHandler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //put in a runnable to bypass Cannot call this method while RecyclerView is computing a layout or scrolling androidx.recyclerview.widget.RecyclerView
                mContactsRecyclerAdapter.addItemAt(contact, mContactsRecyclerAdapter.getItemCount());
            }
        });
    }

    @Override
    public void onContactCheckListener(final Contact contact, final int position) {

        android.os.Handler mHandler = getActivity().getWindow().getDecorView().getHandler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //put in a runnable to bypass Cannot call this method while RecyclerView is computing a layout or scrolling androidx.recyclerview.widget.RecyclerView
                mFavouriteContactsRecyclerAdapter.addItemAt(contact, mFavouriteContactsRecyclerAdapter.getItemCount());
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.contacts_settings_popup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                //navigate to settings fragment
                Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_contactsSettingsFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
