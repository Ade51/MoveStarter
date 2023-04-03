package com.example.MovieStarter.Responses;

import com.example.MovieStarter.Errors.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class RequestResponseT extends RequestResponse<String> {

    public RequestResponseT(String response, ErrorMessage errorMessage) {
        super(response, errorMessage);
    }

    public static RequestResponse okRequestResponse() {
        return fromError(null);
    }
}