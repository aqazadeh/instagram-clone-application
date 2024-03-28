package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.request.UpdateActiveTypeRequest;
import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.dto.response.UserResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.repository.UserRepository;
import com.aqazadeh.intagramclone.service.FollowService;
import com.aqazadeh.intagramclone.service.UserService;
import com.aqazadeh.intagramclone.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 22:11
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final FollowService followService;


    @Override
    public UserResponse getUserById(User user, Long id) {
        User findUser = findById(id);
        UserResponse response = mapper.toResponse(findUser);
        return response;
    }

    @Override
    public void updateActiveType(User user, UpdateActiveTypeRequest request) {
        user.setPrivacy(request.getPrivacy());
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public void follow(User user, Long id) {
        User follow = findById(id);
        followService.follow(user, follow);
        user.setFollowingCount(user.getFollowingCount() + 1);
        follow.setFollowerCount(follow.getFollowerCount() + 1);
        repository.saveAll(List.of(user, follow));
    }

    @Override
    @Transactional
    public void unfollow(User user, Long id) {
        User unfollow = findById(id);
        followService.unfollow(user, unfollow);
        user.setFollowingCount(user.getFollowingCount() - 1);
        unfollow.setFollowerCount(unfollow.getFollowerCount() - 1);
        repository.saveAll(List.of(user, unfollow));

    }

    @Override
    public List<FollowResponse> followings(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return followService.getFollowings(user, pageable);
    }

    @Override
    public List<FollowResponse> followers(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return followService.getFollowers(user, pageable);
    }

    @Override
    @Transactional
    public void updatePostCount(User user, Integer count) {
        user.setPostCount(count);
        repository.save(user);
    }
}
