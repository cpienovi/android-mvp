package com.carlospienovi.mvpproject.presentation.activities;

import android.os.Bundle;

import com.carlospienovi.mvpproject.R;
import com.carlospienovi.mvpproject.presentation.mvp.presenters.DetailPresenter;
import com.carlospienovi.mvpproject.presentation.mvp.views.DetailView;
import com.carlospienovi.mvpproject.utils.BusProvider;
import com.squareup.otto.Bus;

public class DetailActivity extends BaseActivity<DetailPresenter> {

    private DetailPresenter presenter;
    private DetailView view;

    @Override
    protected DetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init(BusProvider.getInstance(), savedInstanceState);
    }

    private void init(Bus bus, Bundle savedInstanceState) {
        view = new DetailView(this, bus, savedInstanceState);
        presenter = new DetailPresenter(view);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        view.onSaveInstanceState(outState);
    }

}
