package main.server.utils.response;

import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

public class SuccessfulAction {
    private final int status;
    private final Object response;

    public SuccessfulAction(int status, Object response) {
        this.status = status;
        this.response = response;
    }

    public ResponseEntity getResponse() {
        return ResponseEntity.status(status).body(response);
    }
}
