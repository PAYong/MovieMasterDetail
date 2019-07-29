package com.example.android.itunesappandroidv2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.itunesappandroidv2.data.MovieRepository;
import com.example.android.itunesappandroidv2.model.Movie;

import java.util.List;

/**
 * This is the ViewModel for Room DB related methods
 */
public class MovieDBViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;
    private LiveData<Movie> selectedMovie;

    public MovieDBViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies =  repository.getAllMovies();
    }

    /**
     * @param movieDB the Movie to be inserted in the DB
     */
    public void insert(Movie movieDB){
        repository.insert(movieDB);
    }

    /**
     * @param movieDB the Movie to be deleted in the DB
     */
    public void delete(Movie movieDB){
        repository.delete(movieDB);
    }

    /**
     * @param trackName the Movie selected in the list
     * @return the Movie found using the trackName specified
     */
    public LiveData<Movie> getMovieByTrackName(String trackName){
        selectedMovie = repository.getMovieByTrackName(trackName);
        return selectedMovie;
    }

    /**
     * @return all the movies from DB
     */
    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }
}
