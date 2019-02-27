package com.rjz.myapplication.retro;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {


    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static RetrofitInterface getServiceClass() {
        return getRetrofit(BASE_URL).create(RetrofitInterface.class);
    }


    public static Retrofit getRetrofit(String url) {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
