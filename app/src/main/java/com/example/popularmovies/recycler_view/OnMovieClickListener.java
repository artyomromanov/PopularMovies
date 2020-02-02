package com.example.popularmovies.recycler_view;
import com.example.popularmovies.model.MovieModel;

public interface OnMovieClickListener {

    void onRecyclerItemClicked(MovieModel model, int position);

}
