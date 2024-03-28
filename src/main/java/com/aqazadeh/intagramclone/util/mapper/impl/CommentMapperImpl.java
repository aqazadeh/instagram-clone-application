package com.aqazadeh.intagramclone.util.mapper.impl;

import com.aqazadeh.intagramclone.dto.request.CreateCommentRequest;
import com.aqazadeh.intagramclone.dto.response.CommentResponse;
import com.aqazadeh.intagramclone.dto.response.UserResponse;
import com.aqazadeh.intagramclone.model.Comment;
import com.aqazadeh.intagramclone.util.mapper.CommentMapper;
import com.aqazadeh.intagramclone.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 16.03.2024
 * Time: 01:05
 */
@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {
    private final UserMapper userMapper;
    @Override
    public Comment toEntity(CreateCommentRequest request) {

        return Comment.builder()
                .comment(request.getMessage())
                .build();
    }

    @Override
    public CommentResponse toResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .user(userMapper.toResponse(comment.getUser()))
                .comment(comment.getComment())
                .children(toResponse(comment.getChildren()))
                .updatedAt(comment.getUpdatedAt())
                .createdAt(comment.getCreatedAt())
                .build();

    }

    public List<CommentResponse> toResponse(List<Comment> comments){
        if (comments == null)
            return null;
        return comments.stream().map(this::toResponse).toList();
    }

}
