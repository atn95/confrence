package main.server.controller;

import main.server.model.CallAnswer;
import main.server.model.CallRequest;
import main.server.model.entity.IceCandidate;
import main.server.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

    @MessageMapping("/answer/{room_id}")
    public ResponseEntity answer(@DestinationVariable("room_id") String room, CallAnswer answer) {
        System.out.println("\n\n" + answer + "\n\n");
        webSocketService.sendAnswer(Long.valueOf(room), answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/candidate/{room_id}")
    public ResponseEntity candidate(@DestinationVariable("room_id") String room, IceCandidate candidate) {
        System.out.println("\n\n" + candidate + "\n\n");
        webSocketService.sendIceCandidate(Long.valueOf(room), candidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @MessageMapping("/reject/{room_id}")
    public ResponseEntity reject(@DestinationVariable("room_id") String room) {
        webSocketService.sendRejectCall(Long.valueOf(room));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
