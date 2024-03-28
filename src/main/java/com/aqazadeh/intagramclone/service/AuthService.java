package com.aqazadeh.intagramclone.service;

import com.aqazadeh.intagramclone.dto.request.UserChangePasswordRequest;
import com.aqazadeh.intagramclone.dto.request.UserLoginRequest;
import com.aqazadeh.intagramclone.dto.request.UserRegisterRequest;
import com.aqazadeh.intagramclone.dto.request.UserResetPasswordRequest;
import com.aqazadeh.intagramclone.dto.response.AuthResponse;
import com.aqazadeh.intagramclone.model.User;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 21:56
 */

public interface AuthService {
    void register(UserRegisterRequest user);

    AuthResponse login(UserLoginRequest request);

    void confirmation(String token);

    void forgetPassword(String username);

    void renewPassword(String otp, UserResetPasswordRequest request);

    void changePassword(User user, UserChangePasswordRequest request);
}
