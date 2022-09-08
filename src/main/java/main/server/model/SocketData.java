package main.server.model;

public class SocketData {
    private final String type;
    private final Object data;

    public SocketData(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
