package com.aqazadeh.intagramclone.dto.response;

import com.aqazadeh.intagramclone.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 21:53
 */
@Data
@Builder
public class FollowResponse {
    Long id;
    User user;
    @JsonProperty("creation_time")
    LocalDateTime createdAt;
}
