package com.aqazadeh.intagramclone.util.mapper.impl;

import com.aqazadeh.intagramclone.dto.request.CreatePostRequest;
import com.aqazadeh.intagramclone.dto.response.PostResponse;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.util.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 01:06
 */
@Component
@RequiredArgsConstructor
public class PostMapperImpl implements PostMapper {
    private final UserMapper userMapper;
    private final MediaMapper mediaMapper;

    @Override
    public Post toEntity(CreatePostRequest request) {

        return Post.builder()
                .content(request.getMessage())
                .build();
    }

    @Override
    public PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .totalLikes(post.getTotalLikes())
                .totalComments(post.getTotalComments())
                .content(post.getContent())
                .media(mediaMapper.toResponse(post.getMedia()))
                .user(userMapper.toResponse(post.getUser()))
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Override
    public List<PostResponse> toResponse(List<Post> posts) {
        return posts.stream().map(this::toResponse).toList();
    }
}
