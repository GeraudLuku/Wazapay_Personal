package com.example.wazapaypersonal.Contacts.Retrofit;

import com.example.wazapaypersonal.Contacts.Models.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

}
