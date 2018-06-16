package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InputInfoMarker;
import com.developer.server.authenticplaces.model.MarkerLatLng;

import java.util.List;

public interface MarkerInfoDao {
    List<MarkerLatLng> getAllLatLngMarkers();

    Marker addMarker(User creator, Double latitude, Double longitude);

    Marker getMarker(Integer id, boolean allContent);
}
