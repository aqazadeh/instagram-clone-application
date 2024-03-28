package com.aqazadeh.intagramclone.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 12.03.2024
 * Time: 20:12
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    Long id;

    String name;

    String username;

    String phoneNumber;

    String email;

    LocalDate birthdate;

    Integer postCount;

    Integer followerCount;

    Integer followingCount;

    @JsonProperty("joined_time")
    LocalDateTime createdAt;
}
