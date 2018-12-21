package com.dilerdesenvolv.cinqagendateste.service;

import com.dilerdesenvolv.cinqagendateste.domain.model.Gallery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GalleryREST {

    @GET("albums")
    Call<List<Gallery>> listAlbuns();

}
