package com.riderguru.rider_guru.libs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sqlException(SQLIntegrityConstraintViolationException exception) {
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

}
