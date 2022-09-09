package main.server.model;

public class CallAnswer {

    private String type;
    private String sdp;
    private String from;

    public CallAnswer(String type, String sdp, String from) {
        this.type = type;
        this.sdp = sdp;
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
