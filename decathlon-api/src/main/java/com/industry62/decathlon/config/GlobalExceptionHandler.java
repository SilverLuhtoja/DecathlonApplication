package com.industry62.decathlon.config;

import com.industry62.decathlon.api.exceptions.InvalidValueException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidValueException.class)
  public ResponseEntity<Object> handleInvalidValueException(InvalidValueException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
