package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ServerException {
    public ForbiddenException(String message, ErrorCodes code) {
        super(HttpStatus.FORBIDDEN, message, code);
    }

    public ForbiddenException(String message) {
        this(message, ErrorCodes.Unknown);
    }
}
