package com.mgl.profile.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(value = UNAUTHORIZED)
public class JwtNotFoundException extends RuntimeException {
    public JwtNotFoundException() {
        super();
    }
    public JwtNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public JwtNotFoundException(String message) {
        super(message);
    }
    public JwtNotFoundException(Throwable cause) {
        super(cause);
    }
}
