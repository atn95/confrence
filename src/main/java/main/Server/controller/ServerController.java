package main.Server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ServerController {

    @Autowired
    public ServerController() {
    }
    @GetMapping(value = "/hello")
    public String test() {
        return "Server is running";
    }

    @GetMapping(value = "/list")
    public int[] getList() {
        return new int[]{1,2,3,4,6,7};
    }
}
