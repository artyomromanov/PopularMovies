package com.example.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.popularmovies.model.MovieModel;
import com.example.popularmovies.model.Results;
import com.example.popularmovies.network.MyConstants;
import com.example.popularmovies.network.RetrofitInstance;
import com.example.popularmovies.recycler_view.MovieAdapter;
import com.example.popularmovies.recycler_view.OnMovieClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    private static final String TAG = "MainActivity";
    private static final String MOVIE_KEY = "Movie";
    public static final String POSITION_KEY = "Position";


    private TextView errorContainer;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Results> myMovieList;
    private boolean shouldShowError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorContainer = findViewById(R.id.error_container);
        recyclerView = findViewById(R.id.rv_movies);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<MovieModel> popularCall = RetrofitInstance.createClient().getMovies(MyConstants.API_KEY);

        popularCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {

                MovieModel pulledModel = response.body();

                movieAdapter = new MovieAdapter(pulledModel, MainActivity.this);

                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, Throwable t) {

                Log.i(TAG, "onFailure: " + t);
                shouldShowError = true;
                showError();

            }
        });
    }

    @Override
    public void onRecyclerItemClicked(MovieModel model, int position) {

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        intent.putExtra(MOVIE_KEY, model);
        intent.putExtra(POSITION_KEY, position);

        startActivity(intent);

    }

    private void showError() {
        if (shouldShowError) {
            errorContainer.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}
   /* public List<Results> createResultsList(List<Results> unformattedResults, List<Results> downloadedResults) {

        List<Results> returnedList = unformattedResults;

        for (int i = unformattedResults.size()-1; i < (unformattedResults.size() + downloadedResults.size()-2); i++) {
            returnedList.add(i, downloadedResults.get(i));
        }
        return returnedList;
    }*/

