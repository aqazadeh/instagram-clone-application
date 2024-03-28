package com.aqazadeh.intagramclone.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 20:47
 */
@Data
public class UserRegisterRequest {
    @NotBlank String name;
    @NotBlank @Size(min = 4) String username;
    @NotBlank @Size(min = 8) String password;
    @NotBlank @Email String email;
    @NotNull @Past LocalDate birthdate;
}
