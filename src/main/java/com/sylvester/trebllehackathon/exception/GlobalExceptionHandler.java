//package com.sylvester.trebllehackathon.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(UserAlreadyExistException.class)
//    public ResponseEntity<Map<String, String>> handleUserAlreadyExistException(UserAlreadyExistException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "User already exist");
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "User not found");
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//}
