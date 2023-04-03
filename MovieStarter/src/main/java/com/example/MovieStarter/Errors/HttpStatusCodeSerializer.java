package com.example.MovieStarter.Errors;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class HttpStatusCodeSerializer extends JsonSerializer<HttpStatus> {

    @Override
    public void serialize(HttpStatus value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.value());
    }
}
