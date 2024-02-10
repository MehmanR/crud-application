package com.example.crudapplication2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handlerMethodArgumentException(MethodArgumentNotValidException exception) {

//        Object[] detailMessageArguments = exception.getDetailMessageArguments();

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();

        body.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerUserNotFoundException(UserNotFoundException ex){

        ErrorResponse errorResponse = ErrorResponse.builder()
                .time(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

}
