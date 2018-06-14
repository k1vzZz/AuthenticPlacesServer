package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.Snapshot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public class SnapshotDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Snapshot addPhoto(Marker marker){
        Session session = sessionFactory.getCurrentSession();
        Snapshot snapshot = new Snapshot();
        snapshot.setUploadDate(new Timestamp(System.currentTimeMillis()));
        snapshot.setMarker(marker);
        snapshot.setAuthorPhoto(marker.getCreator());
        session.saveOrUpdate(snapshot);
        return snapshot;
    }
}
