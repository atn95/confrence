package main.server.model;

import java.util.Arrays;

public class SearchRequest {
    String[] query;
    Long requester;

    public SearchRequest(String[] query, Long requester) {
        this.query = query;
        this.requester = requester;
    }

    public SearchRequest() {
    }

    public String[] getQuery() {
        return query;
    }

    public void setQuery(String[] query) {
        this.query = query;
    }

    public Long getRequester() {
        return requester;
    }

    public void setRequester(Long requester) {
        this.requester = requester;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "query=" + Arrays.toString(query) +
                ", requester=" + requester +
                '}';
    }
}
