package com.sample.sampleapp.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector extends BroadcastReceiver {
    private Context context;
    final String TAG = "NetworkCheckBroadcast";
    android.support.v7.app.AlertDialog.Builder builder;


    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    }
}
