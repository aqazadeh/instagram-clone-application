package com.aqazadeh.intagramclone.util.mapper;

import com.aqazadeh.intagramclone.dto.request.UserRegisterRequest;
import com.aqazadeh.intagramclone.dto.response.UserResponse;
import com.aqazadeh.intagramclone.model.User;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 21:55
 */

public interface UserMapper {

    User toEntity(UserRegisterRequest request);

    UserResponse toResponse(User user);
}
