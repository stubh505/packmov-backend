package com.deloitte.packmov.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuoteException extends RuntimeException {

    public InvalidQuoteException(String message) {
        super("Invalid Quote : " + message);
    }
}