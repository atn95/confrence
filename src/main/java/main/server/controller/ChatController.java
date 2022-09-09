package main.server.controller;

import main.server.model.ChatDTO;
import main.server.model.entity.ChatLog;
import main.server.service.ChatService;
import main.server.constants.ServerConstants;
import main.server.service.WebSocketService;
import main.server.model.SuccessfulResponse;
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
    public SuccessfulResponse sendMessage(@PathVariable Long room_id, @RequestBody ChatDTO messageDTO) throws Exception {
        try {
            System.out.println("\n\n Sending Message to Room:"+room_id+"\n\n");
            ChatLog saved = chatService.logMessage(messageDTO);
            socketService.sendMessageToRoom(room_id, saved);
            return new SuccessfulResponse(200, "Sent Chat Successfully");
        } catch (Exception e) {
            throw e;//new ApiException(400, "Something went wrong sending your message");
        }
    }



}
