package com.aqazadeh.intagramclone.util.mapper.impl;

import com.aqazadeh.intagramclone.dto.request.UserRegisterRequest;
import com.aqazadeh.intagramclone.dto.response.UserResponse;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.util.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 12.03.2024
 * Time: 22:26
 */
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserRegisterRequest request) {

        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .birthdate(request.getBirthdate())
                .build();
    }

    @Override
    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .followingCount(user.getFollowingCount())
                .followerCount(user.getFollowerCount())
                .build();
    }


}
