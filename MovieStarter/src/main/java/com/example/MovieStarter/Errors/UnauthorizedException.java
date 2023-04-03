package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ServerException {
    public UnauthorizedException(String message, ErrorCodes code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }

    public UnauthorizedException(String message) {
        this(message, ErrorCodes.Unknown);
    }
}
