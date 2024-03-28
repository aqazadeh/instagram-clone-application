package com.aqazadeh.intagramclone.controller;

import com.aqazadeh.intagramclone.dto.request.UpdateActiveTypeRequest;
import com.aqazadeh.intagramclone.dto.response.FollowResponse;
import com.aqazadeh.intagramclone.dto.response.Response;
import com.aqazadeh.intagramclone.dto.response.UserResponse;
import com.aqazadeh.intagramclone.model.User;
import com.aqazadeh.intagramclone.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 21:50
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response getUserInfo(@AuthenticationPrincipal User user, @PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(user, id);

        Response response = Response.builder()
                .status(HttpStatus.OK)
                .data(userResponse)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update-active-type")
    public Response updateActiveType(@AuthenticationPrincipal User user, @Valid @RequestBody UpdateActiveTypeRequest request) {
        userService.updateActiveType(user, request);
        Response response = Response.builder()
                .status(HttpStatus.OK)
                .message("success")
                .build();
        return response;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/follow")
    public Response follow(@AuthenticationPrincipal User user, @PathVariable Long id) {
        log.info("follow/controller send follow request userId: {}, follow-user-id: {}", user.getId(), id);
        userService.follow(user, id);
        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("i18n follow create")
                .build();
        log.info("success follow/controller send follow response userId: {}, follow-user-id: {}", user.getId(), id);
        return response;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/unfollow")
    public Response unfollow(@AuthenticationPrincipal User user, @PathVariable Long id) {
        log.info("unfollow/controller send follow request userId: {}, unfollow-user-id: {}", user.getId(), id);
        userService.unfollow(user, id);
        Response response = Response.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("i18n follow removed")
                .build();
        log.info("success unfollow/controller send follow response userId: {}, unfollow-user-id: {}", user.getId(), id);
        return response;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/followings")
    public Response followings(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "0") Integer page) {
        log.info("follow/controller followings request userId: {}", user.getId());
        List<FollowResponse> followings = userService.followings(user, page);
        Response response = Response.builder()
                .status(HttpStatus.OK)
                .data(followings)
                .build();
        log.info("success follow/controller followings response userId: {}", user.getId());
        return response;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/followers")
    public Response followers(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "0") Integer page) {
        log.info("user/controller followers request userId: {}", user.getId());
        List<FollowResponse> followers = userService.followers(user, page);
        Response response = Response.builder()
                .status(HttpStatus.OK)
                .data(followers)
                .build();
        log.info("success user/controller followers response userId: {}", user.getId());
        return response;
    }
}
