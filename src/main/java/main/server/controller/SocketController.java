package main.server.controller;

import main.server.model.CallRequest;
import main.server.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/call/{room_id}")
    public ResponseEntity call(@DestinationVariable("room_id") String room, CallRequest callRequest) {
        System.out.println("\n\n" + callRequest + "\n\n");
        webSocketService.sendCallRequest(Long.valueOf(room), callRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
