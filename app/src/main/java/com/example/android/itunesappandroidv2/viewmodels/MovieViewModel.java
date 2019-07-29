package com.example.android.itunesappandroidv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.android.itunesappandroidv2.data.MovieApi;
import com.example.android.itunesappandroidv2.model.Movie;
import com.example.android.itunesappandroidv2.model.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is the ViewModel for iTunes Api related methods
 */
public class MovieViewModel extends ViewModel {

    //this are the data that we will fetch asynchronously
    public MutableLiveData<MovieList> movieList;
    public MutableLiveData<Movie> movieItem;

    public LiveData<MovieList> getMovieList() {
        if (movieList == null) {
            movieList = new MutableLiveData<MovieList>();
            //we will load it asynchronously from server in this method
            loadMovies();
        }
        return movieList;
    }

    public LiveData<Movie> getMovieSelected(String selectedMovie) {
        if (movieItem == null) {
            movieItem = new MutableLiveData<Movie>();
            //we will load it asynchronously from server in this method
            loadSelectedMovieFromRepo(selectedMovie);
        }
        return movieItem;
    }

    /**
     * This will get all movied based on the specific search
     * This method is using Retrofit to get the data from iTunes
     */
    private void loadMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi api = retrofit.create(MovieApi.class);
        Call<MovieList> call = api.searchMovieRepositories();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                //finally we are setting the list to our MutableLiveData
                movieList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("Retrofit", call.toString());
            }
        });
    }

    /**
     * This will get a specific movie based on the track name
     *  This method is using Retrofit to get the data from iTunes
     * @param selectedMovie The movie selected to be displayed in Detailed view
     */
    private void loadSelectedMovieFromRepo(final String selectedMovie) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi api = retrofit.create(MovieApi.class);
        Call<MovieList> call = api.searchMovieRepositories();


        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                //finally we are setting the item to our MutableLiveData
                MovieList movies = response.body();
                for(Movie movie: movies.getResults()) {
                    if(movie.getTrackName().equals(selectedMovie)) {
                        movieItem.setValue(movie);
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("Retrofit", call.toString());
            }
        });
    }


}
