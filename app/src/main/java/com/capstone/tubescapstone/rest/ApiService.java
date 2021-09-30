package com.capstone.tubescapstone.rest;


import com.capstone.tubescapstone.Model.RootHotelModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("purwakarta/hotel")
    Call<RootHotelModel> getData();


}
