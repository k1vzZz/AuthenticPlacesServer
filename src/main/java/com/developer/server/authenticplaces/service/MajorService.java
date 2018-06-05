package com.developer.server.authenticplaces.service;

import com.developer.server.authenticplaces.dao.MarkerInfoDao;
import com.developer.server.authenticplaces.dao.TestDao;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InfoMarker;
import com.developer.server.authenticplaces.model.MarkerLatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {

    @Autowired
    private TestDao testDao;

    @Autowired
    private MarkerInfoDao markerInfoDao;

    public User show() {
        testDao.testInsert();
        return testDao.testSelect();
    }

    public InfoMarker getMarkersLatLng() {
        return new InfoMarker(markerInfoDao.getAllLatLngMarkers());
    }
}
