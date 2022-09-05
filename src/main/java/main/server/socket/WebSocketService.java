package main.server.socket;

import main.server.chat.ChatLog;
import main.server.socket.messages.ChatMessage;
import main.server.socket.messages.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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
}
