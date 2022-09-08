package main.server.model;

import java.util.Date;

public class ChatReturnDTO {
    private final Long id;
    private final String author;
    private final String Content;
    private final Long roomId;
    private final Date createdAt;
    private final Date updatedAt;

    public ChatReturnDTO(Long id, String author, String content, Long roomId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.author = author;
        Content = content;
        this.roomId = roomId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return Content;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
