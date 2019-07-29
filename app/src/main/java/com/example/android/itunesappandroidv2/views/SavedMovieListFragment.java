package com.example.android.itunesappandroidv2.views;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.adapters.MovieDBAdapter;
import com.example.android.itunesappandroidv2.model.Movie;
import com.example.android.itunesappandroidv2.model.MovieList;
import com.example.android.itunesappandroidv2.viewmodels.MovieDBViewModel;
import java.util.List;


/**
 * A fragment for displaying a list of saved movies from database.
 */
public class SavedMovieListFragment extends Fragment {

    private boolean mTwoPane;
    private MovieDBAdapter adapter;
    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;

    public SavedMovieListFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment
    public static SavedMovieListFragment newInstance() {
        SavedMovieListFragment fragmentSecond = new SavedMovieListFragment();
        return fragmentSecond;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.saved_item_list);
        assert recyclerView != null;

        if (view.findViewById(R.id.saved_item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        fragmentManager = getFragmentManager();

        // This will get the all the saved movie from Room by observing LiveData<List<Movie>> in
        // MovieViewModel
        MovieDBViewModel model = ViewModelProviders.of(this).get(MovieDBViewModel.class);
        model.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                MovieList movieItem = new MovieList();
                movieItem.setResults(movieList);
                movieItem.setResultCount(movieList.size());
                adapter = new MovieDBAdapter(getContext(), movieItem, fragmentManager, mTwoPane);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.saved_item_list_fragment, container, false);
    }

}
