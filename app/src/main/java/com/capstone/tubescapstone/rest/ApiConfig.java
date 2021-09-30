package com.capstone.tubescapstone.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static <retrofit> ApiService getApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.farizdotid.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
