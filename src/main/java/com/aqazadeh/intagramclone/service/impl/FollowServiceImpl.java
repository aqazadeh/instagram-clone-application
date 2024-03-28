package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.Follow;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.repository.FollowRepository;
import com.aqazadeh.intagramclone.service.FollowService;
import com.aqazadeh.intagramclone.util.mapper.FollowMapper;
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
public class FollowServiceImpl implements FollowService {
    private final FollowRepository repository;
    private final FollowMapper mapper;
    @Override
    public void follow(User follower, User following) {

        if (find(follower, following).isEmpty() && !follower.getId().equals(following.getId())) {
            Follow build = Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build();
            repository.save(build);

        } else {
            throw new ApplicationException(ExceptionType.FOLLOWING_ALREADY_EXISTS);
        }
    }

    @Override
    public void unfollow(User user, User unfollow) {
        if (user.getId().equals(unfollow.getId())) {
            throw new ApplicationException(ExceptionType.FOLLOWING_NOT_FOUND);
        }
        Follow following = find(user, unfollow).orElseThrow(() -> new ApplicationException(ExceptionType.FOLLOWING_NOT_FOUND));
        repository.delete(following);
    }

    @Override
    public List<FollowResponse> getFollowings(User user, Pageable pageable) {
        List<Follow> followings = repository.findByFollowing(user, pageable);
        List<FollowResponse> followResponses = followings.stream().map(follow -> mapper.toResponse(follow, follow.getFollowing())).toList();
        return followResponses;
    }

    @Override
    public List<FollowResponse> getFollowers(User user, Pageable pageable) {
        List<Follow> followers = repository.findByFollower(user, pageable);
        List<FollowResponse> followResponses = followers.stream().map(follow -> mapper.toResponse(follow, follow.getFollower())).toList();
        return followResponses;
    }

    private Optional<Follow> find(User follower, User following) {
        return repository.findByFollowingAndFollower(following, follower);
    }
}
