package com.aqazadeh.intagramclone.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 21:03
 */
@Data
public class UserLoginRequest {
    @NotBlank @Size(min = 4) String username;
    @NotBlank @Size(min = 8) String password;

}
