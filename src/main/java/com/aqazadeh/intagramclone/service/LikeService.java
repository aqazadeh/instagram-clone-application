package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.response.LikeResponse;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 02:42
 */

public interface LikeService {
    void like(User user, Post post);
    void unlike(User user, Post post);

    List<LikeResponse> getPostLikes(Post post, Pageable pageable);
}
