package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 02:00
 */

public interface FollowService {
    void follow(User user, User follow);

    void unfollow(User user, User unfollow);

    List<FollowResponse> getFollowings(User user, Pageable pageable);

    List<FollowResponse> getFollowers(User user, Pageable pageable);
}
