package com.developer.server.authenticplaces.model;

import java.util.List;

public class InfoMarker {

    private List<MarkerLatLng> markerLatLngList;

    public InfoMarker(List<MarkerLatLng> markerLatLngList) {
        this.markerLatLngList = markerLatLngList;
    }

    public List<MarkerLatLng> getMarkerLatLngList() {
        return markerLatLngList;
    }

    public void setMarkerLatLngList(List<MarkerLatLng> markerLatLngList) {
        this.markerLatLngList = markerLatLngList;
    }
}
