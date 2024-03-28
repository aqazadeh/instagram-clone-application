package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.request.CreateCommentRequest;
import com.aqazadeh.intagramclone.dto.request.CreatePostRequest;
import com.aqazadeh.intagramclone.dto.response.CommentResponse;
import com.aqazadeh.intagramclone.dto.response.LikeResponse;
import com.aqazadeh.intagramclone.dto.response.PostResponse;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 02:03
 */

public interface PostService {
    void createPost(User user, CreatePostRequest request, MultipartFile[] media);

    List<PostResponse> feed(User user, Integer page);

    List<PostResponse> findAllUserPosts(User user);

    PostResponse getPostById(User user, Long id);

    void deletePost(User user, Long id);

    Post findById(Long postId);

    void like(User user, Long postId);

    void unlike(User user, Long postId);

    void createComment(User user, Long postId, CreateCommentRequest request);

    void deleteComment(User user, Long commentId);

    List<LikeResponse> getPostLikes(User user, Long id, Integer page);

    List<CommentResponse> getPostComments(User user, Long id, Integer page);

    List<PostResponse> getUserPosts(User user, Integer page);
}
