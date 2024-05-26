package com.example.pastbin.api.advice;


import com.example.pastbin.api.response.CustomResponse;
import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.pathtemplate.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ValidationException.class,
            EntityNotFoundException.class,
            AlreadyExistsException.class})
    public ResponseEntity<CustomResponse<?>> handleException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "something went wrong :(";

        if (ex instanceof ValidationException ||
                ex instanceof AlreadyExistsException) {
            status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
        }

        if (ex instanceof EntityNotFoundException){
            status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        }

        return new ResponseEntity<>(new CustomResponse<>(status, message), status);
    }
}