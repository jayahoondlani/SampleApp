package com.sample.sampleapp.network;

import com.sample.sampleapp.pojo.ArticleResponse;
/** This interface is used for accessing data and assigning it into models. */
public interface NetworkResponseListener {

   void onSuccess(ArticleResponse articles);
    void onFailure(String errorMsg);

}
