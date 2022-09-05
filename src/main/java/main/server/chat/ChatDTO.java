package main.server.chat;

public class ChatDTO {
    private Long room_id;
    private Long author_id;
    private String content;


    public ChatDTO(Long room_id, Long author_id, String content) {
        this.room_id = room_id;
        this.author_id = author_id;
        this.content = content;
    }

    public ChatDTO() {
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
                "room_id=" + room_id +
                ", author_id=" + author_id +
                ", content='" + content + '\'' +
                '}';
    }
}
