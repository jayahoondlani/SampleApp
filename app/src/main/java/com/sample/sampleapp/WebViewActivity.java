package com.sample.sampleapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView wvAnnouncement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        displayAnnouncementHtml();
    }
    private  void initView(){

        wvAnnouncement = findViewById(R.id.wv_announcement);
        wvAnnouncement.getSettings().setJavaScriptEnabled(true);

    }

    private void displayAnnouncementHtml(){
        Intent i = getIntent();
        String announcementHtml = i.getStringExtra("announcement_html");

        wvAnnouncement.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });



        if (!TextUtils.isEmpty(announcementHtml)){
            wvAnnouncement.loadUrl(announcementHtml);
        }
        else {
            Toast.makeText(WebViewActivity.this,"No data found.",Toast.LENGTH_LONG);
        }
    }
}
