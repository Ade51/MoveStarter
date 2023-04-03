package com.example.MovieStarter.Errors;

import org.springframework.http.HttpStatus;

public final class CommonErrors {

    public static final ErrorMessage USER_NOT_FOUND = new ErrorMessage(HttpStatus.NOT_FOUND, "User doesn't exist!", ErrorCodes.EntityNotFound);
    public static final ErrorMessage FILE_NOT_FOUND = new ErrorMessage(HttpStatus.NOT_FOUND, "File not found on disk!", ErrorCodes.PhysicalFileNotFound);
    public static final ErrorMessage TECHNICAL_SUPPORT = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred, contact the technical support!", ErrorCodes.TechnicalError);

    private CommonErrors() {
        throw new AssertionError("Cannot instantiate CommonErrors class");
    }
}