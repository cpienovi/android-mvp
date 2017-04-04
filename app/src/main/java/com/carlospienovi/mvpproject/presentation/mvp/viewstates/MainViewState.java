package com.carlospienovi.mvpproject.presentation.mvp.viewstates;

import com.carlospienovi.mvpproject.domain.Post;

import java.util.List;

public final class MainViewState {

    private List<Post> posts;
    private boolean loading;
    private Throwable error;

    public MainViewState(List<Post> posts, boolean loading, Throwable error) {
        this.posts = posts;
        this.loading = loading;
        this.error = error;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public boolean isLoading() {
        return loading;
    }

    public Throwable getError() {
        return error;
    }

}
