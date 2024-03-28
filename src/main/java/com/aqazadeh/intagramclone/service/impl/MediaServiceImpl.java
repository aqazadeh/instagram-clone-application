package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.response.MediaResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.Media;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.enums.MediaType;
import com.aqazadeh.intagramclone.repository.MediaRepository;
import com.aqazadeh.intagramclone.service.MediaService;
import com.aqazadeh.intagramclone.util.mapper.MediaMapper;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:44
 */
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository repository;
    private final MediaMapper mapper;
    private final Cloudinary cloudinary;

    private static List<String> imageTypes = List.of("jpg", "jpeg", "png");
    private static List<String> videoTypes = List.of("mp4", "avi");

    @Override
    @Transactional
    public void save(Post post, MultipartFile[] files) {


        for (MultipartFile file : files) {
            MediaType mediaType = isAllowedFiles(file);
            String url = saveCloudinary(file);
            Media media = Media.builder()
                    .post(post)
                    .type(mediaType)
                    .url(url)
                    .build();
            repository.save(media);
        }
    }

    @Override
    public List<MediaResponse> getByPost(Post post) {
        List<Media> media = repository.findByPost(post);
        List<MediaResponse> response = mapper.toResponse(media);
        return response;
    }

    @Override
    public void delete(Media media) {
        try {
            cloudinary.uploader().destroy(media.getUrl(), ObjectUtils.emptyMap());
            repository.delete(media);
        } catch (IOException e) {
            throw new ApplicationException(ExceptionType.FILE_DELETING_FAIL);
        }
    }

    @Override
    public void deleteAll(List<Media> media) {
        media.forEach(this::delete);
    }

    private MediaType isAllowedFiles(@NotNull MultipartFile file) {


        String fileName = file.getOriginalFilename();
        int length = fileName.split("\\.").length;
        if (length < 2)
            throw new ApplicationException(ExceptionType.FILE_NOT_ALLOWED);

        String extension = fileName.split("\\.")[1];
        if (imageTypes.contains(extension)) {
            return MediaType.IMAGE;
        } else if (videoTypes.contains(extension)) {
            return MediaType.VIDEO;
        }

        throw new ApplicationException(ExceptionType.FILE_NOT_ALLOWED);

    }

    private String saveCloudinary(MultipartFile file) {

        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", "products");
        Map uploadedFile = null;
        try {
            uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        } catch (IOException e) {
            throw new ApplicationException(ExceptionType.FILE_UPLOAD_FAIL);
        }
        return (String) uploadedFile.get("public_id");
    }
}
