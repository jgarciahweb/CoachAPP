package com.coachapp.backend.apis.exceptionhandler;

import com.coachapp.backend.apis.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleIllegalArgument(
            IllegalArgumentException ex) {

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponseDTO.builder()
                                .message(ex.getMessage())
                                .build())
        );
    }
}