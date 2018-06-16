package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Comment;
import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;
import com.developer.server.authenticplaces.model.InputInfoMarker;
import com.developer.server.authenticplaces.model.MarkerLatLng;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MarkerInfoDaoImpl implements MarkerInfoDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public MarkerInfoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MarkerLatLng> getAllLatLngMarkers(){
        String sql = "select new " + MarkerLatLng.class.getName()
                + "(m.id,m.latitude,m.longitude) "
                + "from " + Marker.class.getName() + " m";
        Session session = sessionFactory.getCurrentSession();
        Query<MarkerLatLng> query = session.createQuery(sql, MarkerLatLng.class);
        return query.list();
    }

    @Override
    @Transactional
    public Marker addMarker(User creator, Double latitude, Double longitude) {
        Session session = sessionFactory.getCurrentSession();
        Marker marker = new Marker();
        marker.setCreator(creator);
        marker.setLatitude(latitude);
        marker.setLongitude(longitude);
        session.saveOrUpdate(marker);
        return marker;
    }

    @Override
    @Transactional(readOnly = true)
    public Marker getMarker(Integer id, boolean allContent){
        Session session = sessionFactory.getCurrentSession();
        Marker marker = session.get(Marker.class, id);
        if (allContent) {
            List<Comment> comments = marker.getComments();
            List<Snapshot> snapshots = marker.getSnapshots();
        }
        return marker;
    }
}
