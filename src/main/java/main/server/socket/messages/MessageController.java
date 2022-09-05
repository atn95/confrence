package main.server.socket.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
    private final SimpMessagingTemplate template;

    @Autowired
    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/message/{room}")//client sent to 'ws/messages/{room}'
    //@SendTo("/topic/messages")//client listening to this topic
    public void getMessage(@DestinationVariable int room, final ChatMessage message) {
        System.out.println("Message \n \n " + message.getMessageContent() +" \n \n *******");
        ResponseMessage resp = new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
        this.template.convertAndSend("/topic/room/" + room, resp);
    }
}
