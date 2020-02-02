package com.example.popularmovies.network;

import com.example.popularmovies.model.DetailsModel;
import com.example.popularmovies.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.popularmovies.network.MyConstants.DETAILS_KEY;
import static com.example.popularmovies.network.MyConstants.POPULAR_KEY;

public interface MovieClient {

    @GET(POPULAR_KEY)
    Call<MovieModel> getMovies(@Query("api_key") String key);

    @GET(DETAILS_KEY)
    Call<DetailsModel> getDetails(@Path("movie_id") String id, @Query("api_key") String key);

}
