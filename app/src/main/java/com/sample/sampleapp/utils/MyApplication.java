package com.sample.sampleapp.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/** This class is used to initialize and create universal image loader instance*/
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }
        // initializing image loader
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
