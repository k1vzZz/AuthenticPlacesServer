package com.developer.server.authenticplaces.AuthenticPlacesServer.controller;


import com.developer.server.authenticplaces.AuthenticPlacesServer.entity.User;
import com.developer.server.authenticplaces.AuthenticPlacesServer.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/")
    public User showTest(){
        return majorService.show();
//        return new User("developer");
    }
}
