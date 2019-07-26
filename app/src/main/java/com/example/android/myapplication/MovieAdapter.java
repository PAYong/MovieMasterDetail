package com.example.android.myapplication;

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

import com.example.android.myapplication.dummy.DummyContent;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    Context mCtx;
    List<Movie> heroList;
    FragmentManager mFragmentManager;
    ItemListActivity activity;
    Boolean mTwoPane;
    int adapterPosition;
    public MovieAdapter(ItemListActivity parent, MovieItem heroList, FragmentManager fragmentManager, Boolean mTwoPane) {
        this.mCtx = parent;
        this.heroList = heroList.results;
        mFragmentManager = fragmentManager;
        this.activity = parent;
        this.mTwoPane = mTwoPane;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_list_content, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie hero = heroList.get(position);
        Picasso.get().load(hero.getArtworkUrl100()).error(R.drawable.ic_outline_movie_filter_24px).into(holder.imageView);
        holder.genre.setText(hero.getPrimaryGenreName());
        holder.trackName.setText(hero.getTrackName());
        holder.trackPrice.setText(hero.getTrackPrice());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
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
            adapterPosition = getAdapterPosition();
            itemView.setOnClickListener(mOnClickListener);
        }

    }


    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, heroList.get(adapterPosition).getTrackName());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, "1");

                    context.startActivity(intent);
                }
            }
        };
}