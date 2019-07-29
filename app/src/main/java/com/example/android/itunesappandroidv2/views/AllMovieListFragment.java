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

import com.example.android.itunesappandroidv2.adapters.MovieAdapter;
import com.example.android.itunesappandroidv2.viewmodels.MovieViewModel;
import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.model.MovieList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllMovieListFragment extends Fragment {

    boolean mTwoPane;
    MovieAdapter adapter;
    RecyclerView recyclerView;
    FragmentManager fragmentManager;

    public AllMovieListFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment
    public static AllMovieListFragment newInstance() {
        AllMovieListFragment fragmentFirst = new AllMovieListFragment();
        return fragmentFirst;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.item_list);
        assert recyclerView != null;


        if (view.findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        fragmentManager = getFragmentManager();

        // This will get the all the movie by observing LiveData<MovieList> in MovieViewModel
        MovieViewModel model = ViewModelProviders.of(this).get(MovieViewModel.class);
        model.getMovieList().observe(this, new Observer<MovieList>() {
            @Override
            public void onChanged(@Nullable MovieList movieList) {
                adapter = new MovieAdapter(getContext(), movieList, fragmentManager,
                        getChildFragmentManager(), mTwoPane);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_list_fragment, container, false);
    }
}
