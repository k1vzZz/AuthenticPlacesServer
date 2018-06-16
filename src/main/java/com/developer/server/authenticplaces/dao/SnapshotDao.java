package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;

public interface SnapshotDao {
    Snapshot addPhoto(Marker marker, User authorPhoto);
}
