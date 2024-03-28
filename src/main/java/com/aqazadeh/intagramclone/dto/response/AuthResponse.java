package com.aqazadeh.intagramclone.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 20:45
 */
@Value
public class AuthResponse {
    @JsonProperty("access_token")
    String accessToken;
}
