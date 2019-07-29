package com.example.android.itunesappandroidv2.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.itunesappandroidv2.model.Movie;

import java.util.List;

/**
 * This is the repository for database related methods
 */
public class MovieRepository {

    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;
    private LiveData<Movie> selectedMovie;

    public MovieRepository(Application application){
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public void insert(Movie movieDB){
        new InsertMovieAsyncTask(movieDao).execute(movieDB);
    }

    public void delete(Movie movieDB){
        new DeleteMovieAsyncTask(movieDao).execute(movieDB);
    }

    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }

    public LiveData<Movie> getMovieByTrackName(String trackName){
        selectedMovie = movieDao.getMovieByTrackName(trackName);
        return selectedMovie;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private  MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movieDBS) {
            movieDao.insert(movieDBS[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private  MovieDao movieDao;

        private DeleteMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movieDBS) {
            movieDao.delete(movieDBS[0]);
            return null;
        }
    }
}
