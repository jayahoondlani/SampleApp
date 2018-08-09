package com.sample.sampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sample.sampleapp.adapter.ArticleItemAdapter;
import com.sample.sampleapp.network.ConnectionDetector;
import com.sample.sampleapp.network.DataManager;
import com.sample.sampleapp.network.NetworkResponseListener;
import com.sample.sampleapp.pojo.Result;
import com.sample.sampleapp.pojo.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Initializing views and layouts.
 * Sending request to server and setting data to the adapter main activity works.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView rvArticleView;
    private ProgressBar progressBar;

    List<Result> articleData;
    // creating network response object and overriding methods
    private NetworkResponseListener networkResponseListener = new NetworkResponseListener() {
        @Override
        public void onSuccess(ArticleResponse articles) {
            progressBar.setVisibility(View.GONE);

            if (articles.getResults() != null) {
                articleData = new ArrayList<>();
                articleData.addAll(articles.getResults());
                setDataToView(articleData);
            }

        }

        @Override
        public void onFailure(String errorMsg) {
            progressBar.setVisibility(View.GONE);
            setDataToView(null);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (ConnectionDetector.isConnected(getApplicationContext())) {
            testConnection(true);
            DataManager.loadData(networkResponseListener);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean testConnection(boolean test1) {
        boolean test = test1;
        return test;
    }

    private void initView() {
        rvArticleView = findViewById(R.id.rv_announcements);
        progressBar = findViewById(R.id.progress_bar);

    }

    // to set data on recycler adapter
    private void setDataToView(List<Result> articles) {

        // if onFailure
        if (articles == null) {
            Gson gson = new Gson();
            articles = gson.fromJson(getString(R.string.new_json), new TypeToken<ArticleResponse>() {
            }.getType());
        }

        ArticleItemAdapter announcementItemAdapter = new ArticleItemAdapter(MainActivity.this, articles);
        rvArticleView.setLayoutManager(new LinearLayoutManager(this));
        rvArticleView.setItemAnimator(new DefaultItemAnimator());
        rvArticleView.setAdapter(announcementItemAdapter);
    }
    public int showListSize(){
       return articleData.size();
    }
}
