package com.example.wazapaypersonal.Agent_Listing.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelClass extends ViewModel {

    private MutableLiveData<String> searchKey = new MutableLiveData<>();

    //set and get search key
    public void setSearchKey(String key) {
        searchKey.setValue(key);
    }

    public LiveData<String> getSearchKey() {
        return searchKey;
    }

}
