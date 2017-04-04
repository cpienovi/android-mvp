package com.carlospienovi.mvpproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.carlospienovi.mvpproject.MVPProjectApp;

public abstract class NetworkUtils {

    public static boolean hasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                MVPProjectApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
