package com.example.android.itunesappandroidv2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.itunesappandroidv2.data.MovieRepository;
import com.example.android.itunesappandroidv2.model.Movie;

import java.util.List;

public class MovieDBViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;
    private LiveData<Movie> selectedMovie;

    public MovieDBViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies =  repository.getAllMovies();
    }

    public void insert(Movie movieDB){
        repository.insert(movieDB);
    }

    public void delete(Movie movieDB){
        repository.delete(movieDB);
    }

    public LiveData<Movie> getMovieByTrackName(String trackName){
        selectedMovie = repository.getMovieByTrackName(trackName);
        return selectedMovie;
    }


    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }
}
