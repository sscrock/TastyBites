package com.sourabh.fooddelivery.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ================= HELPER =================
    private Map<String, Object> buildError(String message, int status) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", message);
        error.put("status", status);
        return error;
    }

    // ================= USER ALREADY EXISTS =================
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleUserExists(UserAlreadyExistsException ex) {
        return buildError(ex.getMessage(), 400);
    }

    // ================= INVALID TOKEN =================
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleInvalidToken(InvalidTokenException ex) {
        return buildError(ex.getMessage(), 401);
    }

    // ================= EXPIRED TOKEN =================
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleExpiredToken(TokenExpiredException ex) {
        return buildError(ex.getMessage(), 401);
    }

    // ================= ILLEGAL ARGUMENT =================
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalArgument(IllegalArgumentException ex) {
        return buildError(ex.getMessage(), 400);
    }

    // ================= GENERIC RUNTIME =================
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleRuntime(RuntimeException ex) {
        return buildError(ex.getMessage(), 400);
    }

    // ================= GENERAL EXCEPTION =================
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException() {
        return buildError("Something went wrong", 500);
    }
}