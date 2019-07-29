package com.example.android.itunesappandroidv2.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.itunesappandroidv2.viewmodels.MovieDBViewModel;
import com.example.android.itunesappandroidv2.viewmodels.MovieViewModel;
import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained with a {@link AllMovieListFragment}
 * in two-pane mode (on tablets) or a {@link AllMovieDetailFragment}
 * on handsets.
 */
public class AllMovieDetailFragment extends Fragment {

    public static final String ARG_ITEM_TRACK_NAME = "track_name";
    private String selectedTrackName;
    private MovieDBViewModel movieDBViewModel;
    private Movie selectedMovieItem;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private CollapsingToolbarLayout appBarLayout;

    public AllMovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedTrackName = getArguments().getString(ARG_ITEM_TRACK_NAME);
    }

    @Override
    public void onViewCreated(final @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This will get the specific movie by observing LiveData<Movie> in MovieViewModel
        MovieViewModel model = ViewModelProviders.of(this).get(MovieViewModel.class);
        model.getMovieSelected(selectedTrackName).observe(this,
                new Observer<Movie>() {
                    @Override
                    public void onChanged(@Nullable Movie movie) {
                        if (movie != null) {
                            toolbar.setTitle(movie.getTrackName());
                            ImageView img = view.findViewById(R.id.dialog_icon);

                            //Modify size of url to show high resolution photos
                            movie.setArtworkUrl100(movie.getArtworkUrl100().
                                    replaceAll("100x100bb", "625x625"));
                            Picasso.get().load(movie.getArtworkUrl100()).fit().centerCrop()
                                    .error(R.drawable.ic_outline_movie_filter_24px)
                                    .into(img);
                            ((TextView) view.findViewById(R.id.dialog_primaryGenreName)).setText("Genre: " + movie.getPrimaryGenreName());
                            ((TextView) view.findViewById(R.id.dialog_trackPrice)).setText(
                                    "Track Price: " + movie.getTrackPrice() + movie.getCurrency());
                            ((TextView) view.findViewById(R.id.description)).setText(movie.getLongDescription());
                            ((TextView) view.findViewById(R.id.artist)).setText("Artist: " +
                                    movie.getArtistName());
                            ((TextView) view.findViewById(R.id.collection_name)).setText(
                                    "Collection: " + movie.getCollectionName());
                            ((TextView) view.findViewById(R.id.collection_price)).setText(
                                    "Collection Price: " + movie.getCollectionPrice()+ movie.getCurrency());
                            ((TextView) view.findViewById(R.id.country)).setText("Country: " +
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_item_detail, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.detail_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        movieDBViewModel = new MovieDBViewModel(this.getActivity().getApplication());
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
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
        appBarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar_layout);
        appBarLayout.setTitleEnabled(true);
        // Show the Up button in the action bar.
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        return rootView;
    }
}
