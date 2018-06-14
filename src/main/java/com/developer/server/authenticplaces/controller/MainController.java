package com.developer.server.authenticplaces.controller;


import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InfoMarker;
import com.developer.server.authenticplaces.model.MarkerLatLng;
import com.developer.server.authenticplaces.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest() {
        return majorService.show().getLogin();
//        return new User("developer");
//        return Paths.get("").toAbsolutePath().toString();
    }

    @RequestMapping(value = "/markers", method = RequestMethod.GET)
    public List<MarkerLatLng> showCoordinatesMarkers(){
        return majorService.getMarkersLatLng();
    }

    @RequestMapping(value = "/marker/{id}/update", method = RequestMethod.POST)
    public String updateMarkerContent(@PathVariable Integer id,
                                      @RequestBody String json){
        return "Success";
    }

    @RequestMapping(value = "/marker/new", method = RequestMethod.POST)
    public String newMarker(@RequestBody String json){
        System.out.println(json);
        Marker marker = majorService.addMarker(json);
        return marker.toString();
    }
}
