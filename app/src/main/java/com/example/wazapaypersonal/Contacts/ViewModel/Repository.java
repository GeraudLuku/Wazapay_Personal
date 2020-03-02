package com.example.wazapaypersonal.Contacts.ViewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.wazapaypersonal.Contacts.Models.RetroPhoto;
import com.example.wazapaypersonal.Contacts.Retrofit.GetDataService;
import com.example.wazapaypersonal.Contacts.Retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository repository;

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    private GetDataService mGetDataService;

    public Repository() {
        mGetDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }


    public MutableLiveData<List<RetroPhoto>> getAllPhotos() {
        final MutableLiveData<List<RetroPhoto>> data = new MutableLiveData<>();
        //get the users language, if it exists in the localization api, proceed else fetch english data by default
//        Locale.getDefault().getLanguage();
        mGetDataService.getAllPhotos().enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}

