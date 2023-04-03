package com.example.MovieStarter.Responses;

import com.example.MovieStarter.Errors.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private ErrorMessage error;


    public boolean isOk() {
        return error == null;
    }

    public static ServiceResponse fromError(ErrorMessage error) {
        ServiceResponse response = new ServiceResponse();
        response.setError(error);
        return response;
    }

    public static ServiceResponse forSuccess() {
        return new ServiceResponse();
    }

}

