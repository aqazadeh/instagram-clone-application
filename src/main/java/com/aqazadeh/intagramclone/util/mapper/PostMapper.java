package com.aqazadeh.intagramclone.util.mapper;

import com.aqazadeh.intagramclone.dto.request.CreatePostRequest;
import com.aqazadeh.intagramclone.dto.response.PostResponse;
import com.aqazadeh.intagramclone.model.Post;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:26
 */

public interface PostMapper {
    Post toEntity(CreatePostRequest request);
    PostResponse toResponse(Post post);
    List<PostResponse> toResponse(List<Post> posts);
}
