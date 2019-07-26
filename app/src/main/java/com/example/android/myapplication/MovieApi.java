package com.example.android.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    String BASE_URL = "https://itunes.apple.com/";

    @GET("/repositories")
    Call<List<Movie>> retrieveRepositories();

    @GET("/search?term=star&amp;country=au&amp&media=movie")
    Call<MovieItem> searchMovieRepositories();

    @GET("/search?term=star&amp;country=au&amp&media=movie")
    Call<Movie> searchMovieByTrackNameRepositories(@Query("trackName") String trackName);

}
