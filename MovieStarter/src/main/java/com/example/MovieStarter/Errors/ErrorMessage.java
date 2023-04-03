package com.example.MovieStarter.Errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private ErrorCodes code;

    @JsonSerialize(using = HttpStatusCodeSerializer.class)
    @JsonProperty("status")
    private HttpStatus status;

    public ErrorMessage(HttpStatus status, String message, ErrorCodes code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public ErrorMessage(HttpStatus status, String message) {
        this(status, message, ErrorCodes.Unknown);
    }

    public static ErrorMessage fromException(ServerException exception) {
        return new ErrorMessage((HttpStatus) exception.getStatus(), exception.getMessage());
    }
}
