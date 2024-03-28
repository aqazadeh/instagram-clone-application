package com.aqazadeh.intagramclone.util.mapper.impl;

import com.aqazadeh.intagramclone.dto.response.LikeResponse;
import com.aqazadeh.intagramclone.model.Like;
import com.aqazadeh.intagramclone.util.mapper.LikeMapper;
import com.aqazadeh.intagramclone.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 04:11
 */
@Component
@RequiredArgsConstructor
public class LikeMapperImpl implements LikeMapper {
    private final UserMapper userMapper;
    @Override
    public LikeResponse toResponse(Like like) {
        return LikeResponse.builder()
                .id(like.getId())
                .user(userMapper.toResponse(like.getUser()))
                .createdAt(like.getCreatedAt())
                .build();
    }

    @Override
    public List<LikeResponse> toResponse(List<Like> likes) {
        return likes.stream().map(this::toResponse).toList();
    }
}
