package com.arjava.dcodingcatmovieuiux.request;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by arjava on 11/17/17.
 */

public class ApiClient {

    //url utama API
    private static String BASE_URL = "https://api.themoviedb.org";

    //converter to object
    public static Retrofit getRetrofit(Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
