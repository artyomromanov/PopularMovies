package com.example.popularmovies.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static MovieClient client;
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(MyConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
    public static MovieClient createClient(){
        if (client == null){
             client = getRetrofitInstance().create(MovieClient.class);
        }
        return client;
    }
}
