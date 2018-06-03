package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Marker;
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

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<MarkerLatLng> getAllLatLngMarkers(){
        String sql = "select new " + MarkerLatLng.class.getName()
                + "(m.id,m.latitude,m.longitude) "
                + "from " + Marker.class.getName() + " m";
        Session session = sessionFactory.getCurrentSession();
        Query<MarkerLatLng> query = session.createQuery(sql, MarkerLatLng.class);
        return query.getResultList();
    }
}
