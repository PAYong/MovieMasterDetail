package com.example.android.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously 
    private MutableLiveData<MovieItem> heroList;
    private MutableLiveData<Movie> movieItem;
    //we will call this method to get the data
    public LiveData<MovieItem> getHeroes() {
        //if the list is null 
        if (heroList == null) {
            heroList = new MutableLiveData<MovieItem>();
            //we will load it asynchronously from server in this method
            loadHeroes();
        }

        //finally we will return the list
        return heroList;
    }

    public LiveData<Movie> getHeroByTrackName(String trackName){
        //if the list is null
        if (movieItem == null) {
            movieItem = new MutableLiveData<Movie>();
            //we will load it asynchronously from server in this method
            loadHeroesByTrackName(trackName);
        }

        //finally we will return the list
        return movieItem;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi api = retrofit.create(MovieApi.class);
        Call<MovieItem> call = api.searchMovieRepositories();


        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {

                //finally we are setting the list to our MutableLiveData
                heroList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                Log.d("Retrofit",  call.toString() );
            }
        });
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadHeroesByTrackName(String trackName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi api = retrofit.create(MovieApi.class);
        Call<Movie> call = api.searchMovieByTrackNameRepositories(trackName);


        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                //finally we are setting the list to our MutableLiveData
                movieItem.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Retrofit",  call.toString() );
            }
        });
    }
}
