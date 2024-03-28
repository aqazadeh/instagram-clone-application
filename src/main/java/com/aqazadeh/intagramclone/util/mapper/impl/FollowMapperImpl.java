package com.aqazadeh.intagramclone.util.mapper.impl;

import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.model.Follow;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.util.mapper.FollowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 17.03.2024
 * Time: 19:04
 */
@Component
public class FollowMapperImpl implements FollowMapper {
    @Override
    public FollowResponse toResponse(Follow follow, User user) {
        return FollowResponse.builder()
                .id(follow.getId())
                .user(user)
                .createdAt(follow.getCreatedAt())
                .build();
    }


}
