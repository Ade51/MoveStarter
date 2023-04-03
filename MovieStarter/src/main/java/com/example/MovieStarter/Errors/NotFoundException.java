package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ServerException {
    public NotFoundException(String message, ErrorCodes code) {
        super(HttpStatus.NOT_FOUND, message, code);
    }

    public NotFoundException(String message) {
        this(message, ErrorCodes.Unknown);
    }
}
