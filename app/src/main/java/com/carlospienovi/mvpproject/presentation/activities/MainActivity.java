package com.carlospienovi.mvpproject.presentation.activities;

import android.os.Bundle;

import com.carlospienovi.mvpproject.R;
import com.carlospienovi.mvpproject.presentation.mvp.models.MainModel;
import com.carlospienovi.mvpproject.presentation.mvp.presenters.MainPresenter;
import com.carlospienovi.mvpproject.presentation.mvp.views.MainView;
import com.carlospienovi.mvpproject.utils.BusProvider;
import com.squareup.otto.Bus;

public class MainActivity extends BaseActivity<MainPresenter> {

    private MainPresenter presenter;
    private MainView view;

    @Override
    protected MainPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(BusProvider.getInstance());
    }

    private void init(Bus bus) {
        view = new MainView(this, bus);
        MainModel model = new MainModel(bus);
        presenter = new MainPresenter(view, model);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

}
