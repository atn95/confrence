package main.server.service;

import main.server.model.Account;
import main.server.repository.AccountRepository;
import main.server.model.ChatDTO;
import main.server.model.ChatLog;
import main.server.repository.ChatRepository;
import main.server.model.Room;
import main.server.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;
    private final AccountRepository accountRepository;


    @Autowired
    public ChatService(ChatRepository chatRepository,RoomRepository roomRepository,AccountRepository accountRepository) {
        this.chatRepository = chatRepository;
        this.roomRepository = roomRepository;
        this.accountRepository = accountRepository;
    }

    public ChatLog logMessage(ChatDTO messageDTO) throws Exception {
        System.out.println("called");
        System.out.println(messageDTO);
        Optional<Account> accountSearch = accountRepository.findById(messageDTO.getAuthor_id());
        Optional<Room> roomSearch = roomRepository.findById(messageDTO.getRoom_id());
        if(accountSearch.isEmpty() || roomSearch.isEmpty()) {
            System.out.println("Error");
            throw new Exception("Account or Room is invalid");
        }
        Account author = accountSearch.get();
        Room room = roomSearch.get();
        ChatLog msg = new ChatLog(room, author, messageDTO.getContent());
        chatRepository.save(msg);
        return msg;
    }

    public List<ChatLog> getRoomMessages(Long room_id) {
       List<ChatLog> logs = chatRepository.getLatestMessage(room_id);
       return logs;
    }
}
