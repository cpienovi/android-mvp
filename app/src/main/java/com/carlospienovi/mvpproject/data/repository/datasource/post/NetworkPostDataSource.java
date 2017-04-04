package com.carlospienovi.mvpproject.data.repository.datasource.post;

import com.carlospienovi.mvpproject.data.entity.PostEntity;
import com.carlospienovi.mvpproject.data.networking.PostAPI;
import com.carlospienovi.mvpproject.data.repository.datasource.DataSourceCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkPostDataSource implements PostDataSource {

    @Override
    public void getAll(final DataSourceCallback<List<PostEntity>> callback) {
        PostAPI.getPosts(new Callback<List<PostEntity>>() {
            @Override
            public void onResponse(Call<List<PostEntity>> call, Response<List<PostEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(response.body());
                } else {
                    try {
                        callback.onFailure(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        callback.onFailure(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PostEntity>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
