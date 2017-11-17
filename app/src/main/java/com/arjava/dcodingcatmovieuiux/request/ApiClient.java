package com.arjava.dcodingcatmovieuiux.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by arjava on 11/17/17.
 */

public class ApiClient {

    static String BASE_URL = "https://api.themoviedb.org";

    public static Retrofit getMovie() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
