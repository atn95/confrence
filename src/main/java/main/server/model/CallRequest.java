package main.server.model;

public class CallRequest {
    private String type;
    private String sdp;

    private String from;

    public CallRequest(String type, String sdp, String from) {
        this.type = type;
        this.sdp = sdp;
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public String getSdp() {
        return sdp;
    }

    public String getFrom() {
        return from;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "CallRequest{" +
                "type='" + type + '\'' +
                ", sdp='" + sdp + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
