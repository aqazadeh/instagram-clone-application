package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.request.UpdateActiveTypeRequest;
import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.dto.response.UserResponse;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 21:55
 */


public interface UserService extends UserDetailsService {


    UserResponse getUserById(User user, Long id);

    void updateActiveType(User user, UpdateActiveTypeRequest request);

    User findById(Long id);

    void follow(User user, Long id);

    void unfollow(User user, Long id);

    List<FollowResponse> followings(User user, Integer page);

    List<FollowResponse> followers(User user, Integer page);

    void updatePostCount(User user, Integer count);
}
