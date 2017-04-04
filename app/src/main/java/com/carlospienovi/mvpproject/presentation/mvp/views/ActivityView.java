package com.carlospienovi.mvpproject.presentation.mvp.views;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

abstract class ActivityView {

    private WeakReference<Activity> activityRef;

    public ActivityView(Activity activity) {
        ButterKnife.bind(this, activity);
        activityRef = new WeakReference<>(activity);
    }

    @Nullable
    public Activity getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

}
