package com.example.android.itunesappandroidv2.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.model.Movie;
import com.example.android.itunesappandroidv2.viewmodels.MovieDBViewModel;
import com.example.android.itunesappandroidv2.viewmodels.MovieViewModel;
import com.squareup.picasso.Picasso;

/**
 * An activity representing a single Item detail screen for devices with small screens
 */
public class AllMovieDetailActivity extends AppCompatActivity {
    
    public static final String ARG_ITEM_TRACK_NAME = "track_name";
    private String selectedTrackName;
    private MovieDBViewModel movieDBViewModel;
    private Movie selectedMovieItem;
    private Toolbar toolbar;
    private ActionBar actionBar;
    CollapsingToolbarLayout appBarLayout;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        movieDBViewModel = new MovieDBViewModel(getApplication());
        selectedTrackName = getIntent().getStringExtra(ARG_ITEM_TRACK_NAME);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedMovieItem != null) {
                    movieDBViewModel.insert(selectedMovieItem);
                    Snackbar.make(view, "Movie:" + selectedMovieItem.getTrackName() + " Saved!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                fab.setImageResource(R.drawable.ic_round_favorite_24px);
            }
        });

        appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBarLayout.setTitleEnabled(true);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        // This will get the specific movie from iTunes by observing LiveData<Movie> in
        // MovieViewModel
        MovieViewModel model = ViewModelProviders.of(this).get(MovieViewModel.class);
        model.getMovieSelected(selectedTrackName).observe(this,
                new Observer<Movie>() {
                    @Override
                    public void onChanged(@Nullable Movie movie) {
                        if (movie != null) {
                            toolbar.setTitle(movie.getTrackName());
                            ImageView img = findViewById(R.id.dialog_icon);

                            //Modify size of url to show high resolution photos
                            movie.setArtworkUrl100(movie.getArtworkUrl100().
                                    replaceAll("100x100bb", "625x625"));
                            Picasso.get().load(movie.getArtworkUrl100()).fit().centerCrop()
                                    .error(R.drawable.ic_outline_movie_filter_24px)
                                    .into(img);
                            ((TextView) findViewById(R.id.dialog_primaryGenreName)).setText("Genre: " + movie.getPrimaryGenreName());
                            ((TextView) findViewById(R.id.dialog_trackPrice)).setText(
                                    "Track Price: " + movie.getTrackPrice() + movie.getCurrency());
                            ((TextView) findViewById(R.id.description)).setText(movie.getLongDescription());
                            ((TextView) findViewById(R.id.artist)).setText("Artist: " +
                                    movie.getArtistName());
                            ((TextView) findViewById(R.id.collection_name)).setText(
                                    "Collection: " + movie.getCollectionName());
                            ((TextView) findViewById(R.id.collection_price)).setText(
                                    "Collection Price: " + movie.getCollectionPrice()+ movie.getCurrency());
                            ((TextView) findViewById(R.id.country)).setText("Country: " +
                                    movie.getCountry());
                            if (appBarLayout != null) {

                                appBarLayout.setTitle(movie.getTrackName());

                            }
                            selectedMovieItem = movie;
                            actionBar.setTitle(movie.getTrackName());
                        }
                    }
                });
    }


}
