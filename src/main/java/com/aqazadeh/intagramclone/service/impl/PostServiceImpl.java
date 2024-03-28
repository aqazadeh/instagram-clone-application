package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.request.CreateCommentRequest;
import com.aqazadeh.intagramclone.dto.request.CreatePostRequest;
import com.aqazadeh.intagramclone.dto.response.CommentResponse;
import com.aqazadeh.intagramclone.dto.response.LikeResponse;
import com.aqazadeh.intagramclone.dto.response.PostResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.Comment;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.model.enums.PrivacyType;
import com.aqazadeh.intagramclone.repository.PostRepository;
import com.aqazadeh.intagramclone.service.*;
import com.aqazadeh.intagramclone.util.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final PostMapper mapper;

    private final CommentService commentService;
    private final MediaService mediaService;
    private final LikeService likeService;
    private final UserService userService;

    @Override
    @Transactional
    public void createPost(User user, CreatePostRequest request, MultipartFile[] media) {
        Post post = mapper.toEntity(request);
        post.setUser(user);
        Post save = repository.save(post);
        log.info(save.getId().toString());
        mediaService.save(save, media);
        userService.updatePostCount(user, user.getPostCount() + 1);
    }

    @Override
    public List<PostResponse> feed(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Post> posts = repository.getUserFeed(user, PrivacyType.PUBLIC, pageable);
        return posts.stream().map(mapper::toResponse).toList();
    }


    @Override
    public List<PostResponse> findAllUserPosts(User user) {
        return null;
    }

    @Override
    public PostResponse getPostById(User user, Long id) {
        Post post = findById(id);
        PostResponse response = mapper.toResponse(post);
        return response;
    }

    @Override
    @Transactional
    public void deletePost(User user, Long id) {
        Post post = findById(id);
        if (postIsMine(post, user)) {
            repository.delete(post);
            userService.updatePostCount(user, user.getPostCount() - 1);
        }
    }

    @Override
    public Post findById(Long postId) {
        return repository.findById(postId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.POST_NOT_FOUND));
    }

    @Override
    @Transactional
    public void like(User user, Long postId) {
        Post post = findById(postId);
        likeService.like(user, post);
        post.setTotalLikes(post.getTotalLikes() + 1);
        repository.save(post);
    }

    @Override
    @Transactional
    public void unlike(User user, Long postId) {
        Post post = findById(postId);
        likeService.unlike(user, post);
        post.setTotalLikes(post.getTotalLikes() - 1);
        repository.save(post);
    }

    @Override
    @Transactional
    public void createComment(User user, Long postId, CreateCommentRequest request) {
        Post post = findById(postId);
        commentService.createComment(user, post, request);
        post.setTotalComments(post.getTotalComments() + 1);
        repository.save(post);
    }

    @Override
    @Transactional
    public void deleteComment(User user, Long commentId) {
        Comment comment = commentService.findById(commentId);
        Post post = comment.getPost();
        commentService.deleteComment(user, comment);
        post.setTotalComments(post.getTotalComments() + 1);
        repository.save(post);
    }

    @Override
    public List<LikeResponse> getPostLikes(User user, Long id, Integer page) {
        Post post = findById(id);
        Pageable pageable = PageRequest.of(page, 10);
        List<LikeResponse> response = likeService.getPostLikes(post, pageable);
        return response;
    }

    @Override
    public List<CommentResponse> getPostComments(User user, Long id, Integer page) {
        Post post = findById(id);
        Pageable pageable = PageRequest.of(page, 10);
        List<CommentResponse> response = commentService.getPostComments(post, pageable);
        return response;
    }

    @Override
    public List<PostResponse> getUserPosts(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Post> posts = repository.findByUser(user, pageable);
        return posts.stream().map(mapper::toResponse).toList();
    }

    public boolean postIsMine(Post post, User user) {
        if (!post.getUser().equals(user)) {
            throw new AccessDeniedException("this post not yours");
        }
        return true;
    }
}
