package com.hibernate.hibernatePlayground.exception;

import com.hibernate.hibernatePlayground.Entity.Dto.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Internal Server error : {} " , exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(ErrorResponse.builder().message(exception.getMessage()).build());
    }
}
