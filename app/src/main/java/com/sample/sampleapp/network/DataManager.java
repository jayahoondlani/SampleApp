package com.sample.sampleapp.network;
import android.util.Log;

import com.sample.sampleapp.pojo.Results;
import com.sample.sampleapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

//    public static void loadData(final NetworkResponseListener listener){
//
//        APIInterface apiService =
//                RetrofitApiClient.getClient().create(APIInterface.class);
//
//
//        Call<List<Results>> call = apiService.getAllAnnouncement("Promotions_ipad.htm",
//                "Common.Announcements",
//                "News",
//                "EMC",
//                "News",
//                "J"
//                );
//
//        call.enqueue(new Callback<List<Results>>() {
//            @Override
//            public void onResponse(Call<List<Results>>call, Response<List<Results>> response) {
//                List<Results> results = response.body();
//                listener.onSuccess(results);
//            }
//
//            @Override
//            public void onFailure(Call<List<Results>>call, Throwable t) {
//                // Log error here since request failed
//               if (t!=null){
//                   Log.e("Error", t.toString());
//                   listener.onFailure(t.toString());
//               }
//            }
//        });
//    }
    public static void loadData(final NetworkResponseListener listener){

        APIInterface apiService =
                RetrofitApiClient.getClient().create(APIInterface.class);


        Call<Results> call = apiService.getMostViewedArticles(Constants.API_KEY);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results>call, Response<Results> response) {
                Results results = response.body();
                listener.onSuccess(results);
            }

            @Override
            public void onFailure(Call<Results>call, Throwable t) {
                // Log error here since request failed
                if (t!=null){
                    Log.e("Error", t.toString());
                    listener.onFailure(t.toString());
                }
            }
        });
    }

}
