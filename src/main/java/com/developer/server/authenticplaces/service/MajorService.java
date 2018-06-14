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
        return marker;
    }

    private void decodeBase64FromString(String imageBase64) {
        byte[] decodedString = Base64.decodeBase64(imageBase64);
    }
}
