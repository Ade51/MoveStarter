package com.example.MovieStarter.Errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class ServerException extends Exception {
    public HttpStatusCode status;
    public ErrorCodes code;

    public ServerException(HttpStatus status, String message, ErrorCodes code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public ServerException(HttpStatus status, String message) {
        this(status, message, ErrorCodes.Unknown);
    }
}

