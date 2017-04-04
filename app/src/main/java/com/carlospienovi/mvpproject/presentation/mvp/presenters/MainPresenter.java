package com.carlospienovi.mvpproject.presentation.mvp.presenters;

import com.carlospienovi.mvpproject.domain.Post;
import com.carlospienovi.mvpproject.presentation.adapters.PostAdapter;
import com.carlospienovi.mvpproject.presentation.mvp.models.MainModel;
import com.carlospienovi.mvpproject.presentation.mvp.views.MainView;
import com.carlospienovi.mvpproject.presentation.mvp.viewstates.DetailViewState;
import com.carlospienovi.mvpproject.presentation.mvp.viewstates.MainViewState;
import com.squareup.otto.Subscribe;

public class MainPresenter implements BasePresenter {

    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView mainView, MainModel mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    @Subscribe
    public void onMainViewResumed(MainView.MainViewResumedEvent event) {
        mainView.render(new MainViewState(null, true, null));
        mainModel.fetchPosts();
    }

    @Subscribe
    public void onPostsResponse(MainModel.PostsResponse event) {
        mainView.render(new MainViewState(event.getPosts(), false, event.getError()));
    }

    @Subscribe
    public void onPostClicked(PostAdapter.PostClickedEvent event) {
        Post post = event.getPost();
        DetailViewState detailViewState = new DetailViewState(post.getTitle(), post.getBody());
        mainView.toDetail(detailViewState);
    }

}
