package com.aqazadeh.intagramclone.service.impl;

import com.aqazadeh.intagramclone.dto.request.CreateCommentRequest;
import com.aqazadeh.intagramclone.dto.response.CommentResponse;
import com.aqazadeh.intagramclone.exception.ApplicationException;
import com.aqazadeh.intagramclone.exception.ExceptionType;
import com.aqazadeh.intagramclone.model.Comment;
import com.aqazadeh.intagramclone.model.Post;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.repository.CommentRepository;
import com.aqazadeh.intagramclone.service.CommentService;
import com.aqazadeh.intagramclone.util.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 19:58
 */

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public void createComment(User user, Post post, CreateCommentRequest request) {
        Comment comment = mapper.toEntity(request);
        if (request.getParentId() != null) {
            Comment parent = findById(request.getParentId());
            comment.setParent(parent);
        }
        comment.setUser(user);
        comment.setPost(post);
        repository.save(comment);
    }

    @Override
    public void deleteComment(User user, Comment comment) {
        if (commentIsMine(comment, user)) {
            repository.delete(comment);
        }
    }

    @Override
    public List<CommentResponse> getPostComments(Post post, Pageable page) {
        List<Comment> comments = repository.findByPostAndParentIsNull(post, page);
        List<CommentResponse> response = mapper.toResponse(comments);
        return response;
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.COMMENT_NOT_FOUND));
    }

    private boolean commentIsMine(Comment comment, User user) {
        if (!comment.getUser().equals(user)) {
            throw new ApplicationException(ExceptionType.COMMENT_NOT_FOUND);
        }
        return true;
    }
}
