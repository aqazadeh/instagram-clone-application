package com.aqazadeh.intagramclone.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:05
 */
@Getter
public enum ExceptionType {
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "post.not_found"),
    FILE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "file.not_allowed"),
    FILE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "file.upload.fail"),
    FILE_REQUIRED(HttpStatus.BAD_REQUEST, "file.required"),
    FILE_DELETING_FAIL(HttpStatus.BAD_REQUEST, "file.delete.fail"),

    USERNAME_OR_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "user.fail.account.username_or_email"),
    USER_INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "user.fail.account.invalid.password"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "user.fail.not_found"),
    USER_NOT_ACTIVATED(HttpStatus.FORBIDDEN, "user.fail.account.not_activated"),
    USER_IS_LOCKED(HttpStatus.FORBIDDEN, "user.fail.account.is_locked"),
    USER_MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST, "user.fail.account.mismatch_password"),
    CURRENT_PASSWORD_NEW_PASSWORD(HttpStatus.BAD_REQUEST, "user.fail.account.current_and_new_password_match"),

    EMAIL_SEND_ERROR(HttpStatus.BAD_REQUEST, "email.fail.send"),
    OPT_NOT_FOUND(HttpStatus.BAD_REQUEST, "otp.not_found"),
    OTP_EXPIRED(HttpStatus.BAD_REQUEST, "otp.expired"),
    LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "like.not_found"),
    LIKE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "like.exists"),
    FOLLOWING_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "follow.not_exists"),
    FOLLOWING_NOT_FOUND(HttpStatus.BAD_REQUEST, "follow.not_found"),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "comment.not_found");

    private final HttpStatus status;
    private final String message;

    ExceptionType(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }
}
