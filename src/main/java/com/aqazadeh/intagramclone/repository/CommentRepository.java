package com.aqazadeh.intagramclone.repository;

import com.aqazadeh.intagramclone.model.Comment;
import com.aqazadeh.intagramclone.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostAndParentIsNull(Post post, Pageable page);
}