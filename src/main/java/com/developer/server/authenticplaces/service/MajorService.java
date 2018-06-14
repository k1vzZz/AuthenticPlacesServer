package com.developer.server.authenticplaces.service;

import com.developer.server.authenticplaces.dao.MarkerInfoDao;
import com.developer.server.authenticplaces.dao.SnapshotDaoImpl;
import com.developer.server.authenticplaces.dao.TestDao;
import com.developer.server.authenticplaces.dao.UserDaoImpl;
import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InfoMarker;
import com.developer.server.authenticplaces.model.InputInfoMarker;
import com.developer.server.authenticplaces.model.MarkerLatLng;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MajorService {

    public static final int UPDATE_SUCCESS = -5;

    @Autowired
    private TestDao testDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private MarkerInfoDao markerInfoDao;

    @Autowired
    private SnapshotDaoImpl snapshotDao;

    public User show() {
        testDao.testInsert();
        return testDao.testSelect();
    }

    public List<MarkerLatLng> getMarkersLatLng() {
        return markerInfoDao.getAllLatLngMarkers();
    }

    public Integer addMarker(String json) {
        InputInfoMarker inputInfoMarker = InputInfoMarker.createFromJSON(json);
        String identifierClient = inputInfoMarker.getIdentifierClient();
        User user = userDao.getUserByIdentifierGoogle(identifierClient);
        if (user == null){
            user = userDao.addUser(identifierClient, inputInfoMarker.getLogin(), inputInfoMarker.getUrlImage());
        }
        System.out.println(user);
        Marker marker = new Marker();
        marker.setCreator(user);
        marker.setLatitude(inputInfoMarker.getLatitude());
        marker.setLongitude(inputInfoMarker.getLongitude());
        markerInfoDao.addMarker(marker);
        for (String image : inputInfoMarker.getPhotos()){
            Integer identifierPhoto = snapshotDao.addPhoto(marker).getId();
            System.out.println("IDENTIFIER SSSSSSSSSSSSSSSSSSSSSSSSSSS:" + identifierPhoto);
            saveImage(image, marker.getId(), identifierPhoto);
        }
        return marker.getId();
    }

    private void saveImage(String imageBase64, Integer identifierMarker, Integer identifierSnapshot) {
        byte[] decodedString = Base64.decodeBase64(imageBase64);
        File folder = new File(Paths.get("").toAbsolutePath().toString()
                + File.separator + identifierMarker);
        if (!folder.exists()){
            folder.mkdir();
        }
        File image = new File(folder, identifierSnapshot + ".png");
        try (OutputStream outputStream = new FileOutputStream(image)) {
            outputStream.write(decodedString);
            outputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
