package com.carlospienovi.mvpproject.data.networking;

import com.carlospienovi.mvpproject.data.entity.PostEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PostAPI {

    private static PostService service = RetrofitClient.providePostService();

    public static Call<List<PostEntity>> getPosts(Callback<List<PostEntity>> callback) {
        Call<List<PostEntity>> call = service.getPosts();
        call.enqueue(callback);
        return call;
    }

}
