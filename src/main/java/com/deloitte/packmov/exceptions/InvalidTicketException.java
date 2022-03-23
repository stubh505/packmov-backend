package com.deloitte.packmov.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTicketException extends RuntimeException {

    public InvalidTicketException(String message) {
        super("Invalid Ticket : " + message);
    }
}
