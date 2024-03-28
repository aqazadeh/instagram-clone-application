package com.aqazadeh.intagramclone.util.mapper;

import com.aqazadeh.intagramclone.dto.response.LikeResponse;
import com.aqazadeh.intagramclone.model.Like;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 04:10
 */

public interface LikeMapper {
    LikeResponse toResponse(Like like);
    List<LikeResponse> toResponse(List<Like> likes);
}
