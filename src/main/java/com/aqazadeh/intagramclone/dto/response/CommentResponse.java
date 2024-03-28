package com.aqazadeh.intagramclone.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 02:37
 */
@Data
@Builder
public class CommentResponse {
    Long id;
    String comment;
    UserResponse user;
    List<CommentResponse> children;
    @JsonProperty("creation_time")
    LocalDateTime createdAt;
    @JsonProperty("updated_time")
    LocalDateTime updatedAt;
}
