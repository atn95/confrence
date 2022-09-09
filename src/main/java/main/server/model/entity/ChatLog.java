package main.server.model.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table
public class ChatLog {
    @Id
    @SequenceGenerator(name ="chat_log_sequence", sequenceName = "chat_log_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_log_sequence")
    private Long id;
    //buddy link id
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name =  "author_id")
    private Account author;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


    public ChatLog(Long id, Room room, Account author, String content, Date createdAt, Date updatedAt) {
        this.id = id;
        this.room = room;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ChatLog(Room room, Account author, String content) {
        this.room = room;
        this.author = author;
        this.content = content;
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


    public HashMap<String, Object> getAuthor() {
        HashMap<String, Object> authorVal = new HashMap<>();
        authorVal.put("displayName", author.getDisplayName());
        authorVal.put("id", author.getId());

        return authorVal;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "ChatLog{" +
                "id=" + id +
                ", room=" + room +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
