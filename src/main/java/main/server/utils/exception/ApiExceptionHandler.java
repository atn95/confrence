package main.server.utils.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException e) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("status", e.getStatus());
        res.put("message", e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(res);
    }
}
