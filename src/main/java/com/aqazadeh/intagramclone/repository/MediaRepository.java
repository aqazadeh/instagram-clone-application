package com.aqazadeh.intagramclone.repository;

import com.aqazadeh.intagramclone.model.Media;
import com.aqazadeh.intagramclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByPost(Post post);
}