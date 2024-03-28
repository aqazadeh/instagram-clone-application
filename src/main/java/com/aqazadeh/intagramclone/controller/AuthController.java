package com.aqazadeh.intagramclone.controller;

import com.aqazadeh.intagramclone.dto.request.UserLoginRequest;
import com.aqazadeh.intagramclone.dto.request.UserRegisterRequest;
import com.aqazadeh.intagramclone.dto.request.UserChangePasswordRequest;
import com.aqazadeh.intagramclone.dto.request.UserResetPasswordRequest;
import com.aqazadeh.intagramclone.dto.response.AuthResponse;
import com.aqazadeh.intagramclone.dto.response.Response;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 20:37
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Response register(@Valid @RequestBody UserRegisterRequest request) {
        log.info("auth/controller register request username: " + request.getUsername());
        authService.register(request);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("i18n user create success")
                .build();

        log.info("auth/controller login success username: {}, response: {}", request.getUsername(), response);
        return response;
    }

    @PostMapping("/authentication")
    @ResponseStatus(HttpStatus.OK)
    public Response login(@Valid @RequestBody UserLoginRequest request) {
        log.info("auth/controller login request username: " + request.getUsername());
        AuthResponse responseDto = authService.login(request);
        Response response = Response.builder()
                .status(HttpStatus.OK)
                .data(responseDto)
                .build();
        log.info("auth/controller login success username: " + request.getUsername());
        return response;
    }

    @GetMapping("/confirmation")
    @ResponseStatus(HttpStatus.OK)
    public Response confirmation(@RequestParam String token) {
        log.info("auth/controller confirmation request token: " + token);

        authService.confirmation(token);

        Response response = Response.builder()
                .status(HttpStatus.OK)
                .message("i18n user confirmation success")
                .build();

        log.info("success auth/controller confirmation request token: {}, response: {}", token, response);

        return response;
    }

    @PostMapping("/forget-password/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Response forgetPassword(@PathVariable String username) {
        log.info("auth/controller renewPassword request username: " + username);

        Response response = Response.builder()
                .status(HttpStatus.OK)
                .message("i18n check your mail address reset token have been sended ")
                .build();
        authService.forgetPassword(username);
        log.info("success auth/controller renewPassword request  username: {}, response: {}", username, response);

        return response;
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public Response renewPassword(@RequestParam String otp, @Valid @RequestBody UserResetPasswordRequest request) {
        log.info("auth/controller renewPassword request otp: " + otp);

        Response response = Response.builder()
                .status(HttpStatus.OK)
                .message("i18n password update success ")
                .build();
        authService.renewPassword(otp, request);
        log.info("success auth/controller renewPassword request  otp: {}, response: {}", otp, response);

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/change-password")
    public Response changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody UserChangePasswordRequest request) {
        log.info("auth/controller changePassword request username: " + user.getUsername());

        Response response = Response.builder()
                .status(HttpStatus.OK)
                .message("i18n password changed ")
                .build();
        authService.changePassword(user, request);
        log.info("success auth/controller changePassword request  username: {}, response: {}", user.getUsername(), response);
        return response;
    }
}
