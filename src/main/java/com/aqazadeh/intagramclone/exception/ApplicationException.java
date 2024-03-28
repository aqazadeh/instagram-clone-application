package com.aqazadeh.intagramclone.exception;

import lombok.Getter;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 20:03
 */
@Getter
public class ApplicationException extends RuntimeException {
    private final ExceptionType type;
    public ApplicationException(ExceptionType exceptionType) {
        super(exceptionType.name());
        this.type = exceptionType;
    }
}
