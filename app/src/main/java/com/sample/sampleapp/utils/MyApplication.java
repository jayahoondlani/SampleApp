package com.sample.sampleapp.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.sample.sampleapp.MainActivity;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader(){

        DisplayImageOptions imgOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .build();
        ImageLoaderConfiguration imgConfig = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(imgOptions)
                .build();
        ImageLoader.getInstance().init(imgConfig);

    }

}
