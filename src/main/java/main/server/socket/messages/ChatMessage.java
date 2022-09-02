package main.server.socket.messages;

public class ChatMessage {
    private String messageContent;

    public ChatMessage(String messageContent) {
        this.messageContent = messageContent;
    }

    public ChatMessage() {
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
