package com.carlospienovi.mvpproject.data.repository.datasource.post;


import com.carlospienovi.mvpproject.data.entity.PostEntity;
import com.carlospienovi.mvpproject.data.repository.datasource.DataSourceCallback;

import java.util.List;

public interface PostDataSource {

    void getAll(DataSourceCallback<List<PostEntity>> callback);

}
