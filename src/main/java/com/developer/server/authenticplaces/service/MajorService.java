package com.developer.server.authenticplaces.service;

import com.developer.server.authenticplaces.dao.*;
import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InputInfoMarker;
import com.developer.server.authenticplaces.model.MarkerLatLng;
import com.developer.server.authenticplaces.model.OutputContentMarker;
import com.developer.server.authenticplaces.utils.FileUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

@Service
public class MajorService {

    private static final int UPDATE_SUCCESS = -5;

    private final TestDao testDao;

    private final UserDao userDao;

    private final MarkerInfoDao markerInfoDao;

    private final SnapshotDao snapshotDao;

    private final CommentDao commentDao;

    private final FileUtils fileUtils;

    @Autowired
    public MajorService(TestDao testDao, UserDao userDao, MarkerInfoDao markerInfoDao,
                        SnapshotDao snapshotDao, CommentDao commentDao, FileUtils fileUtils) {
        this.testDao = testDao;
        this.userDao = userDao;
        this.markerInfoDao = markerInfoDao;
        this.snapshotDao = snapshotDao;
        this.commentDao = commentDao;
        this.fileUtils = fileUtils;
    }

    @Transactional
    public User show() {
        testDao.testInsert();
        return testDao.testSelect();
    }

    @Transactional
    public void dropData() {
        testDao.removeData();
    }

    @Transactional
    public List<MarkerLatLng> getMarkersLatLng() {
        return markerInfoDao.getAllLatLngMarkers();
    }

    @Transactional
    public Integer updateMarker(Integer id, String json) {
        System.out.println("UPDATE MARKER");
        InputInfoMarker inputInfoMarker = InputInfoMarker.createFromJSON(json);
        String identifierClient = inputInfoMarker.getIdentifierClient();
        User author = addOrGetUser(identifierClient, inputInfoMarker.getLogin(), inputInfoMarker.getUrlImage());
        Marker marker = markerInfoDao.getMarker(id, false);
        addPhotos(inputInfoMarker.getPhotos(), marker, author);
        addComments(inputInfoMarker.getCommentsText(), inputInfoMarker.getTimes(), marker, author);
        return UPDATE_SUCCESS;
    }

    @Transactional
    public Integer addMarker(String json) {
        System.out.println("ADD MARKER");
        InputInfoMarker inputInfoMarker = InputInfoMarker.createFromJSON(json);
        String identifierClient = inputInfoMarker.getIdentifierClient();
        User user = addOrGetUser(identifierClient, inputInfoMarker.getLogin(), inputInfoMarker.getUrlImage());
        System.out.println(user);
        Marker marker = markerInfoDao.addMarker(user, inputInfoMarker.getLatitude(),
                inputInfoMarker.getLongitude());
        System.out.println("MARKER ID: " + marker.getId());
        addPhotos(inputInfoMarker.getPhotos(), marker, user);
        addComments(inputInfoMarker.getCommentsText(), inputInfoMarker.getTimes(), marker, user);
        return marker.getId();
    }

    @Transactional
    public String getMarkerContentJson(Integer id) {
        System.out.println("GET MARKER CONTENT");
        Marker marker = markerInfoDao.getMarker(id, true);
        OutputContentMarker outputContentMarker = new OutputContentMarker(marker.getCreator());
        outputContentMarker.setSnapshots(marker.getSnapshots());
        outputContentMarker.setComments(marker.getComments());
        Gson gson = new Gson();
        return gson.toJson(outputContentMarker);
    }

    private User addOrGetUser(String identifierClient, String login, String urlImage){
        System.out.println("ADD OR GET USER");
        User user = userDao.getUserByIdentifierGoogle(identifierClient);
        if (user == null){
            System.out.println("ADD OR GET USER: NEW USER");
            user = userDao.addUser(identifierClient, login, urlImage);
        }
        return user;
    }

    private void addPhotos(List<String> imagesBase64, Marker marker, User user){
        if (imagesBase64 == null) return;
        System.out.println("ADD PHOTOS");
        for (String image : imagesBase64){
            Snapshot snapshot = snapshotDao.addPhoto(marker, user);
            System.out.println("IDENTIFIER SNAPSHOT:" + snapshot.getId());
            fileUtils.saveImage(image, marker.getId(), snapshot.getId());
        }
    }

    private void addComments(List<String> textList, List<Long> times, Marker marker, User user){
        if (textList == null || times == null) return;
        System.out.println("ADD COMMENTS");
        Iterator<Long> iterator = times.iterator();
        for (String text : textList){
            if (iterator.hasNext()){
                Long time = iterator.next();
                commentDao.addComment(text, new Timestamp(time), user, marker);
            }
        }
    }
}
