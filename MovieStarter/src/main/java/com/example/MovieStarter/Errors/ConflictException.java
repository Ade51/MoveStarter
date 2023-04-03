package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class ConflictException extends ServerException {
    public ConflictException(String message, ErrorCodes code) {
        super(HttpStatus.CONFLICT, message, code);
    }

    public ConflictException(String message) {
        this(message, ErrorCodes.Unknown);
    }
}
