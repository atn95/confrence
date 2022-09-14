package main.server.service;

import main.server.model.CallAnswer;
import main.server.model.CallRequest;
import main.server.model.entity.ChatLog;
import main.server.model.SocketData;
import main.server.model.entity.IceCandidate;
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

    public void sendMessageToRoom(final Long room, ChatLog message) {
        this.template.convertAndSend("/topic/room/" + room, new SocketData("chat-message", message, room));
    }

    public void sendCallRequest(final Long room, CallRequest callOffer) {
        this.template.convertAndSend("/topic/call/" + room, new SocketData("call-offer",callOffer, room));
    }

    public void sendAnswer(final Long room, CallAnswer answer) {
        this.template.convertAndSend("/topic/call/" + room, new SocketData("call-answer",answer, room));
    }

    public void sendIceCandidate(final Long room, IceCandidate candidate) {
        this.template.convertAndSend("/topic/call/" + room, new SocketData("ice-candidate", candidate, room));
    }

    public void sendRejectCall(final Long room) {
        this.template.convertAndSend("/topic/call/" + room, new SocketData("reject", null, room));
    }

}
