/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.exceptions;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 *
 * @author Veljko
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            details.add(error.getField() + ":" + error.getDefaultMessage());
        });
        String bodyOfResponse = "Error during validation of object " + ex.getBindingResult().getObjectName();
        ApiValidationException exception = new ApiValidationException(bodyOfResponse, HttpStatus.BAD_REQUEST, ZonedDateTime.now(), details);
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = "HttpRequest with that parametars is not allowed ";
        ApiException exception = new ApiException(bodyOfResponse, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SelectionNotFoundException.class})
    protected ResponseEntity<Object> handleSelectionNotFound(SelectionNotFoundException ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        String bodyOfResponse = "Data integrity violation exception";
        ApiException exception = new ApiException(bodyOfResponse + ":" + ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEmptyResultDataAccess(EmptyResultDataAccessException ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WrongCredentials.class})
    protected ResponseEntity<Object> handleWrongCredentials(WrongCredentials ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CannotCreateTransactionException.class})
    protected ResponseEntity<Object> handleDatabaseError(CannotCreateTransactionException ex, WebRequest request) {
        String bodyOfResponse = "Server side error.";
        ApiException exception = new ApiException(bodyOfResponse, HttpStatus.SERVICE_UNAVAILABLE, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {MatchNotFound.class})
    protected ResponseEntity<Object> handleMatchNotFound(MatchNotFound ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = "Wrong JSON format. JSON cannot be parsed.";
        ApiException exception = new ApiException(bodyOfResponse, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BusyUsernameException.class})
    protected ResponseEntity<Object> handleBusyUsernameException(BusyUsernameException ex, WebRequest request) {
        ApiException exception = new ApiException(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_ACCEPTABLE);
    }

}
