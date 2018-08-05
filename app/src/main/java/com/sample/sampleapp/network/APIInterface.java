package com.sample.sampleapp.network;

import com.sample.sampleapp.pojo.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface APIInterface {
//    @GET("/EMC/IPDP/ipdpb.ashx")
//    Call<List<Results>> getAllAnnouncement(@Query("TemplateName") String templateName,
//                                           @Query("p") String p,
//                                           @Query("Handler") String handler,
//                                           @Query("AppName") String appName,
//                                           @Query("Type") String type,
//                                           @Query("F") String f);

    @GET("30.json")
    Call<Results> getMostViewedArticles(@Query("api-key") String apiKey);
}
