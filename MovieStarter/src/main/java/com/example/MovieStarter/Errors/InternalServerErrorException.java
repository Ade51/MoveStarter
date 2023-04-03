package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends ServerException {
    public InternalServerErrorException(String message, ErrorCodes code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, code);
    }

    public InternalServerErrorException(String message) {
        this(message, ErrorCodes.Unknown);
    }

    public InternalServerErrorException() {
        this("Something went wrong!", ErrorCodes.Unknown);
    }
}
