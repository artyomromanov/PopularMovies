package com.example.popularmovies.recycler_view;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.model.MovieModel;
import com.example.popularmovies.network.MyConstants;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

public class MovieHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MovieHolder";

    private TextView tvTitle;
    private ImageView ivPoster;
    private RatingBar rbRating;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        ivPoster = itemView.findViewById(R.id.iv_poster);
        rbRating = itemView.findViewById(R.id.rb_rating);

    }

    public MovieHolder bind(int position, MovieModel model) {
        String title = model.getResults().get(position).getTitle();
        if (title != null) {
            tvTitle.setText(title);
        } else {
            tvTitle.setText(R.string.txt_notitle_error);
        }

        return (this);
    }

    public MovieHolder bindImage(int position, MovieModel model, boolean bigImage) {

        String posterPath, imageSize;

        if (bigImage) {
            imageSize = MyConstants.BASE_FILE_SIZE_LARGE;
        } else {
            imageSize = MyConstants.BASE_FILE_SIZE_SMALL;
        }

        posterPath = MyConstants.BASE_IMAGE_URL + imageSize + model.getResults().get(position).getPosterPath();

        Picasso
                .get()
                .load(posterPath)
                .into(ivPoster);

        Log.i(TAG, posterPath);

        return (this);
    }

    public MovieHolder bindRating(int position, MovieModel model) {

        float rating = (float) (model.getResults().get(position).getVoteAverage() / 2);

        rbRating.setRating(rating);
        rbRating.setIsIndicator(true);

        return (this);
    }

    public int getMovieId(int position, MovieModel model) {

        return model.getResults().get(position).getId();
    }

    public void setClickListener() {

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MovieAdapter.movieClickListener.onRecyclerItemClicked(MovieAdapter.movieModel, getAdapterPosition());
            }
        });
    }
}
