package main.server.chat;

import main.server.constants.ServerConstants;
import main.server.socket.WebSocketService;
import main.server.socket.messages.ChatMessage;
import main.server.utils.exception.ApiException;
import main.server.utils.response.SuccessfulAction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ServerConstants.API_ROUTE + "/chat")
public class ChatController {
    ChatService chatService;
    WebSocketService socketService;

    public ChatController(ChatService chatService, WebSocketService socketService) {
        this.chatService = chatService;
        this.socketService = socketService;
    }

    @PostMapping(value = "/sendchat/{room_id}")
    public SuccessfulAction sendMessage(@PathVariable int room_id, @RequestBody ChatMessage message) {
        try {
            System.out.println("\n\n Sending Message to Room:"+room_id+"\n\n");
            socketService.sendMessageToRoom(room_id, message);
            return new SuccessfulAction(200, "Sent Chat Successfully");
        } catch (Exception e) {
            throw new ApiException(400, "Something went wrong sending your message");
        }
    }

}
