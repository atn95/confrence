package main.server.controller;

import main.server.model.ChatLog;
import main.server.service.ChatService;
import main.server.constants.ServerConstants;
import main.server.utils.exception.ApiException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ServerConstants.API_ROUTE + "/room")
@Transactional
public class RoomController {

    private final ChatService chatService;

    public RoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{id}")
    public List<ChatLog> getMessages(@PathVariable Long id) {
        try {
            return chatService.getRoomMessages(id);
        } catch (Exception e) {
            throw new ApiException(400, "Something went wrong pulling chat messages");
        }
    }
}
