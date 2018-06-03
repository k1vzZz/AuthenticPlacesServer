package com.developer.server.authenticplaces.controller;


import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.MarkerLatLng;
import com.developer.server.authenticplaces.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest() {
        return majorService.show().getLogin();
//        return new User("developer");
    }

    @RequestMapping(value = "/markers", method = RequestMethod.GET)
    public List<MarkerLatLng> showCoordinatesMarkers(){
        List<MarkerLatLng> markerLatLngList = majorService.getMarkersLatLng();
        return markerLatLngList;
    }
}
