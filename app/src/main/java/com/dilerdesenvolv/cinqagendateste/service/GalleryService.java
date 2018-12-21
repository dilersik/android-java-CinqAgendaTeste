package com.dilerdesenvolv.cinqagendateste.service;

import com.dilerdesenvolv.cinqagendateste.domain.model.Gallery;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryService {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private GalleryREST mService = null;
    private static GalleryService mInstance = null;

    public GalleryService() {
        if (mService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mService = retrofit.create(GalleryREST.class);
        }
    }

    public static GalleryService getInstance() {
        if (mInstance == null) {
            mInstance = new GalleryService();
        }
        return mInstance;
    }

    public List<Gallery> getAlbuns() throws Exception {
        return mService.listAlbuns().execute().body();
    }

}
