package com.aqazadeh.intagramclone.util.mapper;

import com.aqazadeh.intagramclone.dto.response.MediaResponse;
import com.aqazadeh.intagramclone.model.Media;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:49
 */

public interface MediaMapper {
    List<MediaResponse> toResponse(List<Media> media);
    MediaResponse toResponse(Media media);
}
