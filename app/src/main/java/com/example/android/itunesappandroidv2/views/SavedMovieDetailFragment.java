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

import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.model.Movie;
import com.example.android.itunesappandroidv2.viewmodels.MovieDBViewModel;
import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link SavedMovieFragment}
 * in two-pane mode (on tablets) or a {@link SavedMovieDetailFragment}
 * on handsets.
 */
public class SavedMovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_TRACK_NAME = "track_name";
    private String selectedTrackName;
    private MovieDBViewModel movieDBViewModel;
    private Movie selectedMovieItem;
    private Toolbar toolbar;
    private ActionBar actionBar;
    CollapsingToolbarLayout appBarLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SavedMovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedTrackName = getArguments().getString(ARG_ITEM_TRACK_NAME);
    }

    @Override
    public void onViewCreated(final @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This will get the specific movie saved from Room by observing LiveData<Movie> in
        // MovieDBViewModel
        MovieDBViewModel model = ViewModelProviders.of(this).get(MovieDBViewModel.class);
        model.getMovieByTrackName(selectedTrackName).observe(this,
                new Observer<Movie>() {
                    @Override
                    public void onChanged(@Nullable Movie movie) {
                        // Show the dummy content as text in a TextView.
                        if (movie != null) {
                            toolbar.setTitle(movie.getTrackName());
                            ImageView img = view.findViewById(R.id.dialog_icon);
                            movie.setArtworkUrl100(movie.getArtworkUrl100().
                                    replaceAll("100x100bb", "625x625"));
                            Picasso.get().load(movie.getArtworkUrl100()).fit().centerCrop()
                                    .error(R.drawable.ic_outline_movie_filter_24px)
                                    .into(img);
                            ((TextView) view.findViewById(R.id.saved_dialog_primaryGenreName)).setText("Genre: " + movie.getPrimaryGenreName());
                            ((TextView) view.findViewById(R.id.saved_dialog_trackPrice)).setText(
                                    "Track Price: " + movie.getTrackPrice() + movie.getCurrency());
                            ((TextView) view.findViewById(R.id.saved_description)).setText(movie.getLongDescription());
                            ((TextView) view.findViewById(R.id.saved_artist)).setText("Artist: " +
                                    movie.getArtistName());
                            ((TextView) view.findViewById(R.id.saved_collection_name)).setText(
                                    "Collection: " + movie.getCollectionName());
                            ((TextView) view.findViewById(R.id.saved_collection_price)).setText(
                                    "Collection Price: " + movie.getCollectionPrice() + movie.getCurrency());
                            ((TextView) view.findViewById(R.id.saved_country)).setText("Country: " +
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

        View rootView = inflater.inflate(R.layout.saved_item_detail, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.saved_detail_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        movieDBViewModel = new MovieDBViewModel(this.getActivity().getApplication());
        final FloatingActionButton fab =
                (FloatingActionButton) rootView.findViewById(R.id.saved_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedMovieItem != null) {
                    movieDBViewModel.delete(selectedMovieItem);
                    Snackbar.make(view, "Movie:" + selectedMovieItem.getTrackName() + " Deleted!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setImageResource(R.drawable.ic_round_favorite_border_24px);
                }
            }
        });
        appBarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.saved_toolbar_layout);
        appBarLayout.setTitleEnabled(true);
        // Show the Up button in the action bar.
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        return rootView;
    }
}

