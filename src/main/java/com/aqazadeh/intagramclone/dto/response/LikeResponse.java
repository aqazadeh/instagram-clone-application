package com.aqazadeh.intagramclone.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 21:36
 */
@Data
@Builder
public class LikeResponse {
    Long id;
    UserResponse user;
    @JsonProperty("liked_time")
    LocalDateTime createdAt;
}
