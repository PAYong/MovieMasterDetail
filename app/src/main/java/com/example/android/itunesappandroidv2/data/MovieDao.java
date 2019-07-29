package com.example.android.itunesappandroidv2.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.itunesappandroidv2.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movieDB);

    @Delete
    void delete(Movie movieDB);

    @Query("SELECT * FROM movie_table ORDER BY trackName DESC")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movie_table WHERE trackName LIKE:trackName")
    LiveData<Movie> getMovieByTrackName(String trackName);
}
