package com.aqazadeh.intagramclone.util.mapper;

import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.model.Follow;
import com.aqazadeh.intagramclone.model.User;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 17.03.2024
 * Time: 18:58
 */

public interface FollowMapper {

    FollowResponse toResponse(Follow follow, User user);

}
