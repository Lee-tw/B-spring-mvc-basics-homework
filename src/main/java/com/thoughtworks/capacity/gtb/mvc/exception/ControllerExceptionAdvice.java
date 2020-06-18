package com.thoughtworks.capacity.gtb.mvc.exception;

import com.google.gson.Gson;
import com.thoughtworks.capacity.gtb.mvc.error.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;


@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(AccountException.class)
    @ResponseBody
    public ResponseEntity<ErrorResult> handleAccountException(AccountException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResult> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(400, message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResult> handleValidationException(ConstraintViolationException e) {
        Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
        String message = "";
        while(iterator.hasNext()) {
            message = iterator.next().getMessage();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(400, message));
    }
}
