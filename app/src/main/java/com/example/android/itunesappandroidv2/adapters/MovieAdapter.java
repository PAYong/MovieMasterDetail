package com.example.android.itunesappandroidv2.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.itunesappandroidv2.R;
import com.example.android.itunesappandroidv2.model.Movie;
import com.example.android.itunesappandroidv2.model.MovieList;
import com.example.android.itunesappandroidv2.views.AllMovieDetailFragment;
import com.example.android.itunesappandroidv2.views.MainActivity;
import com.example.android.itunesappandroidv2.views.AllMovieDetailActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context mCtx;
    private List<Movie> movieList;
    private FragmentManager mFragmentManager;
    private MainActivity activity;
    private Boolean mTwoPane;
    private FragmentManager childFragment;

    public MovieAdapter(Context parent, MovieList movieList, FragmentManager fragmentManager,
                        FragmentManager childFragment,
                        Boolean mTwoPane) {
        this.mCtx = parent;
        this.movieList = movieList.getResults();
        mFragmentManager = fragmentManager;
        this.mTwoPane = mTwoPane;
        this.childFragment = childFragment;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_list_content, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie hero = movieList.get(position);
        Picasso.get().load(hero.getArtworkUrl100()).error(R.drawable.ic_outline_movie_filter_24px).into(holder.imageView);
        holder.genre.setText(hero.getPrimaryGenreName());
        holder.trackName.setText(hero.getTrackName());
        holder.trackPrice.setText(hero.getTrackPrice());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView trackName;
        TextView trackPrice;
        TextView genre;

        public MovieViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.icon);
            trackName = itemView.findViewById(R.id.trackname);
            trackPrice = itemView.findViewById(R.id.trackPrice);
            genre = itemView.findViewById(R.id.primaryGenreName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    String track = movieList.get(itemPosition).getTrackName();

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();

                        arguments.putString(AllMovieDetailFragment.ARG_ITEM_TRACK_NAME,
                                track);
                        AllMovieDetailFragment fragment = new AllMovieDetailFragment();
                        fragment.setArguments(arguments);
                        mFragmentManager.beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, AllMovieDetailActivity.class);
                        intent.putExtra(AllMovieDetailFragment.ARG_ITEM_TRACK_NAME, track);
                        context.startActivity(intent);
                    }
                }
            });
        }

    }

}