package com.arjava.dcodingcatmovieuiux.request;

import com.arjava.dcodingcatmovieuiux.BuildConfig;
import com.arjava.dcodingcatmovieuiux.model.MovieItems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by arjava on 11/17/17.
 */

public interface ApiInterface {

    //endpoint untuk pencarian
    @GET("/3/search/movie?api_key="+ BuildConfig.API_KEY +"&language=en-US&")
    Call<MovieItems> getMovieItems (
            @Query("query") String name_movie
    );

    //endpoint untuk upcoming
    @GET("/3/movie/upcoming?api_key="+ BuildConfig.API_KEY+ "&language=en-US")
    Call<MovieItems> getMovieUpcoming();

    //endpoint untuk now playing
    @GET("/3/movie/now_playing?api_key="+ BuildConfig.API_KEY +"&language=en-ID")
    Call<MovieItems> getNowPlaying();

    //endpoint untuk top rated
    @GET("/3/movie/top_rated?api_key="+ BuildConfig.API_KEY + "&language=en-US")
    Call<MovieItems> getTopRated();

    //endpoint untuk popular
    @GET("/3/movie/popular?api_key="+ BuildConfig.API_KEY + "&language=en-US")
    Call<MovieItems> getPopular();

    //endpoint untuk latest
    @GET("/3/movie/latest?api_key="+ BuildConfig.API_KEY + "&language=en-US")
    Call<MovieItems> getLatest();
}
