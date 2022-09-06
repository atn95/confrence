package main.server.chat;

import main.server.account.Account;
import main.server.account.AccountRepository;
import main.server.friends.Room;
import main.server.friends.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//       List<ChatReturnDTO> returns = new ArrayList<>();
//       for(ChatLog log: logs) {
//           returns.add(
//                   new ChatReturnDTO(log.getId(), (String) log.getAuthor().get("name"), log.getContent(),log.getRoom().getId(), log.getUpdatedAt(),log.getCreatedAt() )
//           );
//       }
       return logs;
    }
}
