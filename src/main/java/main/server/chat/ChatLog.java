package main.server.chat;

import main.server.account.Account;
import main.server.friends.Room;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class ChatLog {
    @Id
    @SequenceGenerator(name ="chat_log_sequence", sequenceName = "chat_log_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_log_sequence")
    private Long id;
    //buddy link id
    @ManyToOne
    @JoinColumn(name="room")
    private Room room;
    @ManyToOne
    @JoinColumn(name =  "author")
    private Account author;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ChatLog(Long id, Room room, Account author, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.room = room;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ChatLog(Room room, Account author, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.room = room;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ChatLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
