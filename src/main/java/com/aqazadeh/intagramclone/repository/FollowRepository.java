package com.aqazadeh.intagramclone.repository;

import com.aqazadeh.intagramclone.model.Follow;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingAndFollower(User following, User follower);

    List<Follow> findByFollowing(User user, Pageable pageable);

    List<Follow> findByFollower(User user, Pageable pageable);

}