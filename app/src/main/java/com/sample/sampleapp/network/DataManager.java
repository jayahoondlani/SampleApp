package com.sample.sampleapp.network;



import android.util.Log;

import com.sample.sampleapp.pojo.ArticleResponse;
import com.sample.sampleapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**  This class is used for  loading data from API using APi key.
 * After getting result the data is passed to the networkresponselistener onsuccess method */
public class DataManager {

    // For loading data from api
    public static void loadData(final NetworkResponseListener listener) {

        APIInterface apiService =
                RetrofitApiClient.getClient().create(APIInterface.class);

        // Retrofit request call
        Call<ArticleResponse> call = apiService.getMostViewedArticles(Constants.API_KEY);

        call.enqueue(new Callback<ArticleResponse>() {

            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse articleResponse = response.body();
                listener.onSuccess(articleResponse);
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                // Log error here since request failed
                if (t != null) {
                    Log.e("Error", t.toString());
                    listener.onFailure(t.toString());
                }
            }
        });
    }

}
