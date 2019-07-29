package com.example.android.itunesappandroidv2.data;
import com.example.android.itunesappandroidv2.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    String BASE_URL = "https://itunes.apple.com/";

    @GET("/search?term=star&amp;country=au&amp&media=movie")
    Call<MovieList> searchMovieRepositories();
}
