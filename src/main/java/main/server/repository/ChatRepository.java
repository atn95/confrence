package main.server.repository;

import main.server.model.entity.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatLog, Long> {

    @Query(value = "SELECT * FROM chat_log WHERE chat_log.room_id =:room_id ORDER BY chat_log.created_at DESC LIMIT 20", nativeQuery = true)
    List<ChatLog> getLatestMessage(@Param("room_id") Long room_id);


}
