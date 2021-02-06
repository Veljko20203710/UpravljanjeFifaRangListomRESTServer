/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.exceptions;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Veljko
 */
public class ApiValidationException {
    
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
    private final List<String> details;

    public ApiValidationException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime,List<String> details) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public List<String> getDetails() {
        return details;
    } 
}
