package com.sample;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sample.sampleapp.WebViewActivity;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebViewTest {


    @Rule
    public ActivityTestRule<WebViewActivity> mActivityRule = new ActivityTestRule<>(WebViewActivity.class);



}