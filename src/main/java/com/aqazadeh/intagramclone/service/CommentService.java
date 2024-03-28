package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.request.CreateCommentRequest;
import com.aqazadeh.intagramclone.dto.response.CommentResponse;
import com.aqazadeh.intagramclone.model.Comment;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 02:36
 */

public interface CommentService {
    void createComment(User user, Post post, CreateCommentRequest request);

    void deleteComment(User user, Comment commentId);

    List<CommentResponse> getPostComments(Post post, Pageable page);

    Comment findById(Long id);
}
