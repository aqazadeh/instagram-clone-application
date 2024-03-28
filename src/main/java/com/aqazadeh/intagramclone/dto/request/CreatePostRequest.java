package com.aqazadeh.intagramclone.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 02:09
 */
@Data
public class CreatePostRequest {
    @NotBlank String message;
}
