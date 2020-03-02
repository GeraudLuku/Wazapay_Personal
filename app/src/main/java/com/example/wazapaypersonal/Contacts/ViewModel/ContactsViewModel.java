package com.example.wazapaypersonal.Contacts.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wazapaypersonal.Contacts.Models.RetroPhoto;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    MutableLiveData<List<RetroPhoto>> photos;
    private Repository repository;

    public void init() {
        if (photos != null) {
            return;
        }
        repository = Repository.getInstance();
        photos = repository.getAllPhotos();

    }

    public LiveData<List<RetroPhoto>> getData() {
        return photos;
    }

}