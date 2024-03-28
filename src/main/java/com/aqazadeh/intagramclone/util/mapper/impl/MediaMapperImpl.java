package com.aqazadeh.intagramclone.util.mapper.impl;

import com.aqazadeh.intagramclone.dto.response.MediaResponse;
import com.aqazadeh.intagramclone.model.Media;
import com.aqazadeh.intagramclone.util.mapper.MediaMapper;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 01:05
 */
@Component
@RequiredArgsConstructor
public class MediaMapperImpl implements MediaMapper {
    private final Cloudinary cloudinary;

    @Override
    public List<MediaResponse> toResponse(List<Media> mediaList) {

        return mediaList.stream().map(this::toResponse).toList();
    }

    @Override
    public MediaResponse toResponse(Media media) {
        String url = cloudinary.url().secure(true).generate(media.getUrl());
        return MediaResponse.builder()
                .id(media.getId())
                .mediaType(media.getType())
                .url(url)
                .build();
    }
}
