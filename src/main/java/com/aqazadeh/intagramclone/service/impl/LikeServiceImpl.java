package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.response.LikeResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.Like;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.repository.LikeRepository;
import com.aqazadeh.intagramclone.service.LikeService;
import com.aqazadeh.intagramclone.util.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 01:17
 */
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final LikeMapper mapper;

    @Override
    public void like(User user, Post post) {
        if(find(user, post).isEmpty()){
            Like like = Like.builder().post(post).user(user).build();
            repository.save(like);
        }else {
            throw new ApplicationException(ExceptionType.LIKE_ALREADY_EXISTS);
        }
    }

    @Override
    public void unlike(User user, Post post) {
        Like like = find(user, post).orElseThrow(() -> new ApplicationException(ExceptionType.LIKE_NOT_FOUND));
        repository.delete(like);
    }

    @Override
    public List<LikeResponse> getPostLikes(Post post, Pageable pageable) {
        List<Like> likes = repository.findByPost(post, pageable);
        return likes.stream().map(mapper::toResponse).toList();
    }

    private Optional<Like> find(User user, Post post){
        return repository.findByUserAndPost(user, post);
    }
}
