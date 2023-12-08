package com.example.bankingapp.controller;

import com.example.bankingapp.exceptions.NoUserExistsException;
import com.example.bankingapp.exceptions.UserExistsException;
import com.example.bankingapp.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class BankingAppExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> Errors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                Errors.toString(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserExistsException userExistsException) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                userExistsException.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
                userNotFoundException.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NoUserExistsException.class)
    public ResponseEntity<ExceptionResponse> handleException(NoUserExistsException noUserExistsException) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
                noUserExistsException.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(), System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
