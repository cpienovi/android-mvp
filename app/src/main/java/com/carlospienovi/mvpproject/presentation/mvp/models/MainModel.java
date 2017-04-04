package com.carlospienovi.mvpproject.presentation.mvp.models;

import com.carlospienovi.mvpproject.data.repository.PostDataRepository;
import com.carlospienovi.mvpproject.data.repository.datasource.DataSourceCallback;
import com.carlospienovi.mvpproject.domain.Post;
import com.squareup.otto.Bus;

import java.util.List;

public class MainModel {

    private Bus bus;
    private PostDataRepository postRepository;

    public MainModel(Bus bus) {
        this.bus = bus;
        this.postRepository = PostDataRepository.INSTACE;
    }

    public void fetchPosts() {
        postRepository.getAll(new DataSourceCallback<List<Post>>() {
            @Override
            public void onResponse(List<Post> response) {
                bus.post(new PostsResponse(response, null));
            }

            @Override
            public void onFailure(Throwable error) {
                bus.post(new PostsResponse(null, error));
            }
        });
    }

    public static final class PostsResponse {

        private List<Post> posts;
        private Throwable error;

        PostsResponse(List<Post> posts, Throwable error) {
            this.posts = posts;
            this.error = error;
        }

        public List<Post> getPosts() {
            return posts;
        }

        public Throwable getError() {
            return error;
        }

    }

}
