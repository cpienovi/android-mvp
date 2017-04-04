package com.carlospienovi.mvpproject.data.entity.mapper;

import android.support.annotation.Nullable;

import com.carlospienovi.mvpproject.data.entity.PostEntity;
import com.carlospienovi.mvpproject.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostEntityMapper {

    @Nullable
    public Post transform(@Nullable PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }

        return new Post(postEntity.getTitle(), postEntity.getBody());
    }

    public List<Post> transform(@Nullable List<PostEntity> postEntities) {
        if (postEntities == null) {
            return new ArrayList<>(0);
        }

        List<Post> result = new ArrayList<>(postEntities.size());
        for (PostEntity postEntity : postEntities) {
            Post post = transform(postEntity);
            if (post != null) {
                result.add(post);
            }
        }

        return result;
    }

}
