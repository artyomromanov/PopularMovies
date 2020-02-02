package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.popularmovies.model.DetailsModel;
import com.example.popularmovies.recycler_view.MovieHolder;
import com.example.popularmovies.model.MovieModel;
import com.example.popularmovies.network.MyConstants;
import com.example.popularmovies.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    public static final String MOVIE_KEY = "Movie";
    public static final String POSITION_KEY = "Position";

    private TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.details_activity);

        MovieModel model = getIntent().getParcelableExtra(MOVIE_KEY);
        int adapterPos = getIntent().getIntExtra(POSITION_KEY, 0);

        View v = findViewById(R.id.view_details);
        tvOverview = findViewById(R.id.tv_overview);

        MovieHolder detailsHolder = new MovieHolder(v);

        detailsHolder
                .bind(adapterPos, model)
                .bindImage(adapterPos, model, true)
                .bindRating(adapterPos, model);

        String clickedID = Integer.toString(detailsHolder.getMovieId(adapterPos, model));

        Call<DetailsModel> detailsModelCall = RetrofitInstance.createClient().getDetails(clickedID, MyConstants.API_KEY);

        detailsModelCall.enqueue(new Callback<DetailsModel>() {
            @Override
            public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {

                DetailsModel pulledDetails = response.body();

                tvOverview.setText(pulledDetails.getOverview());
                tvOverview.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onFailure(Call<DetailsModel> call, Throwable t) {

                Log.i(TAG, t.getMessage());
            }
        });


        Log.i(TAG, clickedID);


    }
}
