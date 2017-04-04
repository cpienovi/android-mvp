package com.carlospienovi.mvpproject.data.networking;

import com.carlospienovi.mvpproject.data.entity.PostEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {

    String BASE_URL = "http://jsonplaceholder.typicode.com";

    @GET("/posts")
    Call<List<PostEntity>> getPosts();

}
