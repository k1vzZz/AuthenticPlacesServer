package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Repository
public class SnapshotDaoImpl implements SnapshotDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public SnapshotDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Snapshot addPhoto(Marker marker, User authorPhoto){
        Session session = sessionFactory.getCurrentSession();
        Snapshot snapshot = new Snapshot();
        snapshot.setUploadDate(new Timestamp(System.currentTimeMillis()));
        snapshot.setMarker(marker);
        snapshot.setAuthorPhoto(authorPhoto);
        session.saveOrUpdate(snapshot);
        snapshot.setPath(Paths.get("").toAbsolutePath().toString() +
                File.separator + marker.getId() + File.separator + snapshot.getId());
        snapshot.setUrl("https://authenticplaces.herokuapp.com/markers/" + marker.getId() + '/' + snapshot.getId());
        return snapshot;
    }
}
