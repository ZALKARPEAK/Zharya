package com.example.pastbin.exceptions.handler;

import com.example.pastbin.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.lang.IllegalStateException;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ExceptionResponse
                .builder()
                .httpStatus(HttpStatus.CONFLICT)
                .exceptionClassName(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse alreadyExistsException(AlreadyExistsException e){
        return ExceptionResponse.builder()
                .exceptionClassName(e.getClass().getSimpleName())
                .httpStatus(HttpStatus.CONFLICT)
                .build();
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e){
        return ExceptionResponse.builder()
                .exceptionClassName(e.getClass().getSimpleName())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse badCredentialException(BadCredentialsException e){
        return ExceptionResponse.builder()
                .exceptionClassName(e.getClass().getSimpleName())
                .httpStatus(HttpStatus.FORBIDDEN)
                .build();
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleAuthenticationException(AuthenticationException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .exceptionClassName(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(DataUpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleDataUpdateException(DataUpdateException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .exceptionClassName(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse InvalidPasswordException(InvalidPasswordException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleNotFoundException(BadCredentialsException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse HandleConflictException(HttpClientErrorException.Conflict e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .exceptionClassName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
}