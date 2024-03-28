package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.response.MediaResponse;
import com.aqazadeh.intagramclone.model.Media;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:38
 */

public interface MediaService {
    void save(Post post, MultipartFile[] singleMedia);

    List<MediaResponse> getByPost(Post post);

    void delete(Media media);

    void deleteAll(List<Media> media);
}
