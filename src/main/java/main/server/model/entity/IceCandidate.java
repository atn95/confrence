package main.server.model.entity;

public class IceCandidate {
    private String type;
    private String candidate;
    private String from;

    public IceCandidate(String type, String candidate, String from) {
        this.type = type;
        this.candidate = candidate;
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "IceCandidate{" +
                "type='" + type + '\'' +
                ", candidate='" + candidate + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
