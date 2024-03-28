package com.aqazadeh.intagramclone.repository;

import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.model.enums.PrivacyType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN User u ON p.user.id = u.id LEFT JOIN Follow f ON p.user = f.following WHERE (f.follower = :user OR u.privacy = :privacy)")
    List<Post> getUserFeed(User user, PrivacyType privacy, Pageable pageable);
}