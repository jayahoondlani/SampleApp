package com.sample.sampleapp.network;

import com.sample.sampleapp.pojo.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface APIInterface {

    @GET("30.json")
    Call<Results> getMostViewedArticles(@Query("api-key") String apiKey);
}
