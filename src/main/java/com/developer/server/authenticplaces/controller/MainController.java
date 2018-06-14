package com.developer.server.authenticplaces.controller;


import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InfoMarker;
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

//    @Autowired
//    private MajorService majorService;
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String showTest() {
//        return majorService.show().getLogin();
////        return new User("developer");
////        return Paths.get("").toAbsolutePath().toString();
//    }
//
//    @RequestMapping(value = "/markers", method = RequestMethod.GET)
//    public List<MarkerLatLng> showCoordinatesMarkers(){
//        return majorService.getMarkersLatLng();
//    }
//
//    @RequestMapping(value = "/marker/{id}/update", method = RequestMethod.POST)
//    public Integer updateMarkerContent(@PathVariable Integer id,
//                                      @RequestBody String json){
//        return MajorService.UPDATE_SUCCESS;
//    }
//
//    @RequestMapping(value = "/marker/new", method = RequestMethod.POST)
//    public Integer newMarker(@RequestBody String json){
//        System.out.println(json);
//        return majorService.addMarker(json);
//    }
//
//    @RequestMapping(value = "/marker/{id}/{imageId}", method = RequestMethod.GET)
//    public @ResponseBody
//    void getInsideImage(@PathVariable Integer id,
//                        @PathVariable Integer imageId,
//                        HttpServletResponse response) throws IOException {
//        File file = new File(Paths.get("").toAbsolutePath().toString()
//                + File.separator + id + File.separator + imageId + ".png");
//        InputStream fileInputStream = new FileInputStream(file);
//        response.setContentType("image/*");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Disposition", "attachment; filename=" + id);
//        response.setHeader("Content-Length", String.valueOf(file.length()));
//        FileCopyUtils.copy(fileInputStream, response.getOutputStream());
//    }
}
