package com.example.MovieStarter.Responses;

import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.entities.User;

import java.util.Optional;

public class RequestResponse<T> {
    /**
     * This is the response to the request, if an error occurred this should be null.
     */
    private T response;
    /**
     * This is the error message for the error that occurred while responding to the request, if no error occurred this should be null.
     */
    private ErrorMessage errorMessage;

    public RequestResponse(T response, ErrorMessage errorMessage) {
        this.response = response;
        this.errorMessage = errorMessage;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static RequestResponse<String> fromError(ErrorMessage error) {
        return error != null ? new RequestResponse<>(null, error) : new RequestResponse<>("Ok", null);
    }

    public static RequestResponse<String> fromServiceResponse(ServiceResponseT<?> serviceResponse) {
        return fromError(serviceResponse.getError());
    }

    public static <T> RequestResponse<T> fromServiceResponseOfType(ServiceResponseT<T> serviceResponse) {
        return serviceResponse.getError() != null ? new RequestResponse<>(null, serviceResponse.getError()) : new RequestResponse<>(serviceResponse.getResult(), null);
    }
}

