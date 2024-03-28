package com.aqazadeh.intagramclone.util.mapper;

import com.aqazadeh.intagramclone.dto.request.CreateCommentRequest;
import com.aqazadeh.intagramclone.dto.response.CommentResponse;
import com.aqazadeh.intagramclone.model.Comment;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:12
 */

public interface CommentMapper {
    Comment toEntity(CreateCommentRequest request);
    CommentResponse toResponse(Comment comment);
    List<CommentResponse> toResponse(List<Comment> comment);
}
