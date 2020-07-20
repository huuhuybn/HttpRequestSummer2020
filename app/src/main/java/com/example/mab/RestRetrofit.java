package com.example.mab;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestRetrofit {
    public static Retrofit retrofit;

    public static Retrofit getInstance(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
