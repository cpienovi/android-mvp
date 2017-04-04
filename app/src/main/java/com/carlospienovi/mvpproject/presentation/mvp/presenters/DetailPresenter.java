package com.carlospienovi.mvpproject.presentation.mvp.presenters;

import com.carlospienovi.mvpproject.presentation.mvp.views.DetailView;

public class DetailPresenter implements BasePresenter {

    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

}
