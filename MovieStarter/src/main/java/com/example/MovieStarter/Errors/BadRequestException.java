package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServerException {
    public BadRequestException(String message, ErrorCodes code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }

    public BadRequestException(String message) {
        this(message, ErrorCodes.Unknown);
    }
}
