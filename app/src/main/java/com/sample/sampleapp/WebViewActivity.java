package com.sample.sampleapp;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * This activity is used to show articles webpages
 */
public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog dialog;
    private Toolbar toolbar;
    private ProgressBar progressBar;
   private String articleHtml;
   private boolean isHtmlValid = false;
   public static String DEFAULT_URL = ".html";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        displayArticleHtml();
        setUpToolbar();

    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_arrow);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    private void initView() {

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress);

    }

    // to show the url data
    private void displayArticleHtml() {
        Intent i = getIntent();
        articleHtml = i.getStringExtra("article_html");

        // to set webview
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WebViewActivity.this, description, Toast.LENGTH_SHORT).show();
                isHtmlValid = false;
                isValidHtml(isHtmlValid);
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                progressBar.setVisibility(View.GONE);
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                isHtmlValid = false;
                isValidHtml(isHtmlValid);
            }

            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                isHtmlValid = true;
                isValidHtml(isHtmlValid);

            }
        });

        if (!TextUtils.isEmpty(articleHtml)) {
            webView.loadUrl(articleHtml);

        } else {
            Toast.makeText(WebViewActivity.this, "No data found.", Toast.LENGTH_LONG);
        }
    }
        public boolean intentHasAdresses(){
        boolean hasHtml = articleHtml.length() >-1 ;
        return hasHtml;
    }
    public boolean isValidHtml(boolean isHtmlValid){
        return isHtmlValid;
    }

}
