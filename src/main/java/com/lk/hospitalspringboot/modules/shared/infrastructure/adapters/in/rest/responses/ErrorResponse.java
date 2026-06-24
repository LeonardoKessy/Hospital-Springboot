package com.lk.hospitalspringboot.modules.shared.infrastructure.adapters.in.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String code,
        String message,
        String path,
        Map<String, String> fields
) {

    public static ErrorResponse of(
            HttpStatus status,
            String code,
            String message,
            String path
    ) {
        return new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                code,
                message,
                path,
                null
        );
    }

    public static ErrorResponse bundle (
        HttpStatus status,
        String code,
        String message,
        String path,
        Map<String, String> fields
    ) {
        return new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                code,
                message,
                path,
                fields
        );
    }

}
