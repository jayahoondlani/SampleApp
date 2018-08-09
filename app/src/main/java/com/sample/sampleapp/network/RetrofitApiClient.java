package com.sample.sampleapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** For eshtablishing connection retrofit client is created*/
public class RetrofitApiClient {

    public static final String BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/";
    private static Retrofit retrofit = null;

    // for creating retrofit request
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
