package main.server.model;

import main.server.model.entity.Account;

public class LoginResponse {
    private final String token;
    private final Account user;

    public LoginResponse(Account user, String token) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Account getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
