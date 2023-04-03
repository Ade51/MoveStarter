package com.example.MovieStarter.Responses;

import com.example.MovieStarter.Errors.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseT<T> extends ServiceResponse {

    private T result;



    public static <T> ServiceResponseT<T> createError(ErrorMessage error) {
        ServiceResponseT<T> response = new ServiceResponseT<>();
        response.setError(error);
        return response;
    }

    public static <T> ServiceResponseT<T> forSuccess(T data) {
        ServiceResponseT<T> response = new ServiceResponseT<>();
        response.setResult(data);
        return response;
    }

    public ServiceResponse toResponse() {
        return ServiceResponse.fromError(getError());
    }

    public T getResult() {
        return result;
    }
}