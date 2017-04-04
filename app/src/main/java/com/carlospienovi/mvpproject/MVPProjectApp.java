package com.carlospienovi.mvpproject;

import android.app.Application;

public class MVPProjectApp extends Application {

    private static MVPProjectApp instance;

    public static MVPProjectApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
