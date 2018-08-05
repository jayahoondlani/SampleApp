package com.sample.sampleapp.network;

import com.sample.sampleapp.pojo.Results;

/**
 * Created by saurabh-pc on 6/14/2018.
 */

public interface NetworkResponseListener {

   void onSuccess(Results articles);
    void onFailure(String errorMsg);

}
