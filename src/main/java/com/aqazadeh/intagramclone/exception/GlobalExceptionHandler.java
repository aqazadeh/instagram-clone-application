package com.aqazadeh.intagramclone.exception;

import com.aqazadeh.intagramclone.dto.response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 17.03.2024
 * Time: 03:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public Response handle(ApplicationException e) {
        String message = e.getType().getMessage();
        HttpStatus status = e.getType().getStatus();
        return Response.builder().status(status).message(message).build();
    }

    @ExceptionHandler(Exception.class)
    public  Response handle(Exception e) {
        return Response.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getField() + " " + f.getDefaultMessage()).toList();
        return Response.builder().status(HttpStatus.BAD_REQUEST).data(errors).build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public Response handle(AuthenticationException e) {
        return Response.builder().status(HttpStatus.UNAUTHORIZED).message(e.getMessage()).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Response handle(AccessDeniedException e) {
        return Response.builder().status(HttpStatus.FORBIDDEN).message(e.getMessage()).build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Response handle(ExpiredJwtException e){
        return Response.builder().status(HttpStatus.UNAUTHORIZED).message(e.getMessage()).build();
    }

    @ExceptionHandler(MalformedJwtException.class)
    public Response handle(MalformedJwtException e){
        return Response.builder().status(HttpStatus.UNAUTHORIZED).message(e.getMessage()).build();
    }
}
