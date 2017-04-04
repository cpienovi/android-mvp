package com.carlospienovi.mvpproject.data.repository;

import com.carlospienovi.mvpproject.data.entity.PostEntity;
import com.carlospienovi.mvpproject.data.entity.mapper.PostEntityMapper;
import com.carlospienovi.mvpproject.data.repository.datasource.DataSourceCallback;
import com.carlospienovi.mvpproject.data.repository.datasource.post.NetworkPostDataSource;
import com.carlospienovi.mvpproject.domain.Post;
import com.carlospienovi.mvpproject.domain.repository.PostRepository;

import java.util.List;

public class PostDataRepository implements PostRepository {

    public static final PostDataRepository INSTACE = new PostDataRepository();

    private NetworkPostDataSource networkDataSource;
    private PostEntityMapper entityMapper;

    private PostDataRepository() {
        this.networkDataSource = new NetworkPostDataSource();
        this.entityMapper = new PostEntityMapper();
    }

    @Override
    public void getAll(final DataSourceCallback<List<Post>> callback) {
        networkDataSource.getAll(new DataSourceCallback<List<PostEntity>>() {
            @Override
            public void onResponse(List<PostEntity> response) {
                callback.onResponse(entityMapper.transform(response));
            }

            @Override
            public void onFailure(Throwable error) {
                callback.onFailure(error);
            }
        });
    }

}
