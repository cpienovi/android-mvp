package com.carlospienovi.mvpproject.domain.repository;

import com.carlospienovi.mvpproject.data.repository.datasource.DataSourceCallback;
import com.carlospienovi.mvpproject.domain.Post;

import java.util.List;

public interface PostRepository {

    void getAll(DataSourceCallback<List<Post>> callback);

}
