package com.aqazadeh.intagramclone.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 23:54
 */

@Data
public class UserResetPasswordRequest {

    @NotBlank @Size(min = 8)
    @JsonProperty("new_password")
    String newPassword;

    @NotBlank @Size(min = 8)
    @JsonProperty("new_password_repeat")
    String newPasswordRepeat;
}
