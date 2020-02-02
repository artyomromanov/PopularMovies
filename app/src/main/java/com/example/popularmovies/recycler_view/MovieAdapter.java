package com.example.popularmovies.recycler_view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.MainActivity;
import com.example.popularmovies.R;
import com.example.popularmovies.model.MovieModel;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

    public static MovieModel movieModel;
    public static OnMovieClickListener movieClickListener;

    private static final String TAG = "MovieAdapter";

    public MovieAdapter(MovieModel movieModel, OnMovieClickListener movieClickListener) {

        MovieAdapter.movieModel = movieModel;
        MovieAdapter.movieClickListener = movieClickListener;

    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        holder
                .bind(position, movieModel)
                .bindImage(position, movieModel, false)
                .bindRating(position, movieModel)
                .setClickListener();

        if (holder.getAdapterPosition()>movieModel.getResults().size()){

        }


    }

    @Override
    public int getItemCount() {
        if(movieModel != null)
        return movieModel.getResults().size();
        else return 0;
    }

}
