package com.developer.server.authenticplaces.AuthenticPlacesServer.controller;


import com.developer.server.authenticplaces.AuthenticPlacesServer.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "/")
    public User showTest(){
        return new User("developer");
    }
}
