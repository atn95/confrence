package main.server.service;

import main.server.model.CallRequest;
import main.server.model.ChatLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.template = messagingTemplate;
    }

    public void sendMessageToRoom(final int room, ChatLog message) {
        this.template.convertAndSend("/topic/room/" + room, message);
    }

    public void sendCallRequest(final int room, CallRequest callOffer) {

    }
}
