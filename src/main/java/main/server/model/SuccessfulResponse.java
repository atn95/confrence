package main.server.model;

import org.springframework.http.ResponseEntity;

public class SuccessfulResponse {
    private final int status;
    private final Object response;

    public SuccessfulResponse(int status, Object response) {
        this.status = status;
        this.response = response;
    }

    public ResponseEntity getResponse() {
        return ResponseEntity.status(status).body(response);
    }
}
