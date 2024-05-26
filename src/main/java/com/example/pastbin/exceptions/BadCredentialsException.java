package com.example.pastbin.exceptions;

import lombok.Getter;

@Getter
public class BadCredentialsException extends RuntimeException{
    private final String messageCode;
    private final Object[] args;
    //HttpStatus add

    public BadCredentialsException(String messageCode, Object[] args) {
        super(messageCode);
        this.messageCode = messageCode;
        this.args = args;
    }
    public BadCredentialsException(String messageCode) {
        super(messageCode);
        this.messageCode = messageCode;
        this.args = null;
    }

}
