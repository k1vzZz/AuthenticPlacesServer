package com.developer.server.authenticplaces.controller;


import com.developer.server.authenticplaces.model.MarkerLatLng;
import com.developer.server.authenticplaces.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class MainController {

    private final MajorService majorService;

    @Autowired
    public MainController(MajorService majorService) {
        this.majorService = majorService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest() {
        return majorService.show().getLogin();
//        return new User("developer");
//        return Paths.get("").toAbsolutePath().toString();
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public String drop(){
        majorService.dropData();
        return "drop";
    }

    @RequestMapping(value = "/markers", method = RequestMethod.GET)
    public List<MarkerLatLng> showCoordinatesMarkers(){
        return majorService.getMarkersLatLng();
    }

    @RequestMapping(value = "/markers/{id}/update", method = RequestMethod.POST)
    public Integer updateMarkerContent(@PathVariable Integer id,
                                      @RequestBody String json){
        return majorService.updateMarker(id, json);
    }

    @RequestMapping(value = "/markers/new", method = RequestMethod.POST)
    public Integer newMarker(@RequestBody String json){
        System.out.println(json);
        return majorService.addMarker(json);
    }

    @RequestMapping(value = "/markers/{id}", method = RequestMethod.GET)
    public String showMarkerContent(@PathVariable Integer id){
        return majorService.getMarkerContentJson(id);
    }

    @RequestMapping(value = "/markers/{id}/{imageId}", method = RequestMethod.GET)
    public @ResponseBody
    void getInsideImage(@PathVariable Integer id,
                        @PathVariable Integer imageId,
                        HttpServletResponse response) throws IOException {
        File file = new File(Paths.get("").toAbsolutePath().toString()
                + File.separator + id + File.separator + imageId + ".jpg");
//        File file = new File(File.separator + id + File.separator + imageId + ".png");
        InputStream fileInputStream = new FileInputStream(file);
        response.setContentType("image/*");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + id + '-' + imageId);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(fileInputStream, response.getOutputStream());
    }
}
