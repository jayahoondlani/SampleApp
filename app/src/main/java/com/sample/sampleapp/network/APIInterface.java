package com.sample.sampleapp.network;

import com.sample.sampleapp.pojo.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/** Passing the API key to Okhttp request*/
public interface APIInterface {
    // assigning key to okhttp url
    @GET("30.json")
    Call<ArticleResponse> getMostViewedArticles(@Query("api-key") String apiKey);
}
