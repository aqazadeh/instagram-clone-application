package com.aqazadeh.intagramclone.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:11
 */
@Data
public class CreateCommentRequest {
    @JsonProperty("parent_id")
    Long parentId;
    String message;
}
