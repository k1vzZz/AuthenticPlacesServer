package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.model.MarkerLatLng;

import java.util.List;

public interface MarkerInfoDao {
    List<MarkerLatLng> getAllLatLngMarkers();
}
