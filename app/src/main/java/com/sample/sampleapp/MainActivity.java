package com.sample.sampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sample.sampleapp.adapter.ArticleItemAdapter;
import com.sample.sampleapp.network.DataManager;
import com.sample.sampleapp.network.NetworkResponseListener;
import com.sample.sampleapp.pojo.Result;
import com.sample.sampleapp.pojo.Results;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView rvArticleView;
private ProgressBar progressBar;


private NetworkResponseListener networkResponseListener = new NetworkResponseListener() {
    @Override
    public void onSuccess(Results articles) {
        progressBar.setVisibility(View.GONE);

        if (articles.getResults()!= null) {
            List<Result> articleData = new ArrayList<>();
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
        DataManager.loadData(networkResponseListener);

    }


    private void initView(){
        rvArticleView = findViewById(R.id.rv_announcements);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setDataToView(List<Result> articles){
        if (articles==null ){
            Gson gson = new Gson();
            articles = gson.fromJson(getString(R.string.new_json), new TypeToken<Results>(){}.getType());
        }

        ArticleItemAdapter announcementItemAdapter = new ArticleItemAdapter(MainActivity.this,articles);
        rvArticleView.setLayoutManager(new LinearLayoutManager(this));
        rvArticleView.setItemAnimator(new DefaultItemAnimator());
        rvArticleView.setAdapter(announcementItemAdapter);
    }
}
