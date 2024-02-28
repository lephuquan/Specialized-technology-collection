package com.practice.learningJPA.exceptions;

import org.springframework.http.ResponseEntity;

public class ResponseExceptionBuilder {
    public static ResponseEntity<Object> build(ErrorMessage apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
