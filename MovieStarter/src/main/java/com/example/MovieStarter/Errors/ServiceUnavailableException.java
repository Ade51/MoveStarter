package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends ServerException {
    public ServiceUnavailableException(String message, ErrorCodes code) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message, code);
    }

    public ServiceUnavailableException(String message) {
        this(message, ErrorCodes.Unknown);
    }
}
