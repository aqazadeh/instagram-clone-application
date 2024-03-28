package com.aqazadeh.intagramclone.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 21:53
 */
@Data
public class UserChangePasswordRequest {
    @NotBlank @Size(min = 8)
    @JsonProperty("current_password")
    String currentPassword;

    @NotBlank @Size(min = 8)
    @JsonProperty("new_password")
    String newPassword;
    @NotBlank @Size(min = 8)
    @JsonProperty("new_password_repeat")
    String newPasswordRepeat;
}
