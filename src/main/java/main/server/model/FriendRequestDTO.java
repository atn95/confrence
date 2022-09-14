package main.server.model;

public class FriendRequestDTO {
    private Long requester;
    private Long receiver;

    public FriendRequestDTO(Long requester, Long receiver) {
        this.requester = requester;
        this.receiver = receiver;
    }

    public Long getRequester() {
        return requester;
    }

    public void setRequester(Long requester) {
        this.requester = requester;
    }

    public Long getReciever() {
        return receiver;
    }

    public void setReciever(Long reciever) {
        this.receiver = reciever;
    }
}
