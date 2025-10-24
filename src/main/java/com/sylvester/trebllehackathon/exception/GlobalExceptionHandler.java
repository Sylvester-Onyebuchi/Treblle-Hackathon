package com.sylvester.trebllehackathon.exception;

import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import com.sylvester.trebllehackathon.repository.ApiUnsuccessfulLogsRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ApiUnsuccessfulLogsRepository apiUnsuccessfulLogsRepository;

    public GlobalExceptionHandler(ApiUnsuccessfulLogsRepository apiUnsuccessfulLogsRepository) {
        this.apiUnsuccessfulLogsRepository = apiUnsuccessfulLogsRepository;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        ApiUnsuccessfulLogs apiUnsuccessfulLogs = new ApiUnsuccessfulLogs();
        apiUnsuccessfulLogs.setPath(request.getRequestURI());
        apiUnsuccessfulLogs.setMethod(request.getMethod());
        apiUnsuccessfulLogs.setDescription(ex.getMessage());
        apiUnsuccessfulLogs.setErrorType(ex.getClass().getName());
        apiUnsuccessfulLogs.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiUnsuccessfulLogs.setResponseTime(System.currentTimeMillis());
        apiUnsuccessfulLogs.setDetectedAt(LocalDateTime.now());

        apiUnsuccessfulLogsRepository.save(apiUnsuccessfulLogs);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Error", ex.getMessage());
        responseBody.put("path", request.getRequestURI());
        responseBody.put("method", request.getMethod());
        responseBody.put("timestamp", LocalDateTime.now());
        return  new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, HttpServletRequest request) {
        ApiUnsuccessfulLogs apiUnsuccessfulLogs = new ApiUnsuccessfulLogs();
        apiUnsuccessfulLogs.setPath(request.getRequestURI());
        apiUnsuccessfulLogs.setMethod(request.getMethod());
        apiUnsuccessfulLogs.setDescription(ex.getMessage());
        apiUnsuccessfulLogs.setErrorType(ex.getClass().getName());
        apiUnsuccessfulLogs.setResponseCode(HttpStatus.BAD_REQUEST.value());
        apiUnsuccessfulLogs.setResponseTime(System.currentTimeMillis());
        apiUnsuccessfulLogs.setDetectedAt(LocalDateTime.now());

        apiUnsuccessfulLogsRepository.save(apiUnsuccessfulLogs);

        Map<String, Object> response = new HashMap<>();
       response.put("Error", ex.getMessage());
       response.put("Path", request.getRequestURI());
       response.put("Method", request.getMethod());
       response.put("ResponseCode", HttpStatus.BAD_REQUEST.value());
       response.put("ResponseTime", System.currentTimeMillis());
       response.put("DetectedAT", LocalDateTime.now());


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ApiUnsuccessfulLogs apiUnsuccessfulLogs = new ApiUnsuccessfulLogs();
        apiUnsuccessfulLogs.setPath(request.getRequestURI());
        apiUnsuccessfulLogs.setMethod(request.getMethod());
        apiUnsuccessfulLogs.setDescription(ex.getMessage());
        apiUnsuccessfulLogs.setErrorType(ex.getClass().getName());
        apiUnsuccessfulLogs.setResponseCode(HttpStatus.NOT_FOUND.value());
        apiUnsuccessfulLogs.setResponseTime(System.currentTimeMillis());
        apiUnsuccessfulLogs.setDetectedAt(LocalDateTime.now());

        apiUnsuccessfulLogsRepository.save(apiUnsuccessfulLogs);

        Map<String, String> response = new HashMap<>();
        response.put("Error: ", ex.getMessage());
        response.put("Path: ", request.getRequestURI());
        response.put("Method: ", request.getMethod());
        response.put("ResponseCode: ", String.valueOf(HttpStatus.NOT_FOUND.value()));
        response.put("ResponseTime: ", String.valueOf(System.currentTimeMillis()));
        response.put("DetectedAt: ", String.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
