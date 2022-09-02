package main.server.socket.messages;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/message")//client sent to 'ws/messages'
    @SendTo("/topic/messages")//client listening to this topic
    public ResponseMessage getMessage(final ChatMessage message) {
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
