package com.carlospienovi.mvpproject.presentation.activities;

import android.support.v7.app.AppCompatActivity;

import com.carlospienovi.mvpproject.presentation.mvp.presenters.BasePresenter;
import com.carlospienovi.mvpproject.utils.BusProvider;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected abstract P getPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.register(getPresenter());
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusProvider.unregister(getPresenter());
    }

}
