package com.developer.server.authenticplaces.service;

import com.developer.server.authenticplaces.dao.MarkerInfoDao;
import com.developer.server.authenticplaces.dao.TestDao;
import com.developer.server.authenticplaces.dao.UserDaoImpl;
import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InfoMarker;
import com.developer.server.authenticplaces.model.InputInfoMarker;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {

    @Autowired
    private TestDao testDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private MarkerInfoDao markerInfoDao;

    public User show() {
        testDao.testInsert();
        return testDao.testSelect();
    }

    public InfoMarker getMarkersLatLng() {
        return new InfoMarker(markerInfoDao.getAllLatLngMarkers());
    }

    public Marker addMarker(String json) {
        InputInfoMarker inputInfoMarker = InputInfoMarker.createFromJSON(json);
        Integer identifierClient = Integer.parseInt(inputInfoMarker.getIdentifierClient());
//        if (userDao.getUserById(identifierClient) == null){
//            userDao.addUser(identifierClient, inputInfoMarker.getLogin(), inputInfoMarker.getUrlImage());
//        }
//        Integer identifierMarker = markerInfoDao.saveNewMarker(inputInfoMarker);
        Marker marker = new Marker();
        User user = new User(identifierClient, inputInfoMarker.getLogin(), inputInfoMarker.getUrlImage());
        marker.setCreator(user);
        marker.setLatitude(inputInfoMarker.getLatitude());
        marker.setLongitude(inputInfoMarker.getLongitude());
        markerInfoDao.addMarker(marker);
        return marker;
    }

    private void decodeBase64FromString(String imageBase64) {
        byte[] decodedString = Base64.decodeBase64(imageBase64);
    }
}
