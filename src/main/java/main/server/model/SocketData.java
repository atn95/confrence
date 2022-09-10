package main.server.model;

public class SocketData {
    private final String type;
    private final Object data;

    private final Long room;

    public SocketData(String type, Object data, Long room) {
        this.type = type;
        this.data = data;
        this.room = room;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public Long getRoom() {
        return room;
    }
}
