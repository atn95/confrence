package main.Server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @Autowired
    public ServerController() {
    }
    @GetMapping
    public String test() {
        return "Server is running";
    }
}
