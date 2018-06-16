package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Comment;
import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.Snapshot;
import com.developer.server.authenticplaces.entity.User;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TestDaoImpl implements TestDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public TestDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void testInsert() {
        Session session = sessionFactory.getCurrentSession();
        User user = new User();
        user.setLogin("lucky");
        user.setIdentifierClient("111111");
        user.setUrl("url");
        user.setId(1);
        session.replicate(user, ReplicationMode.OVERWRITE);

        Marker marker = new Marker(44.002312, 56.876432);
        session.persist(marker);
    }

    @Transactional
    public User testSelect() {
        Session session = sessionFactory.getCurrentSession();
        User user;
        user = session.get(User.class, 1);
        return user;
    }

    @Override
    @Transactional
    public void removeData() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "delete from ";
        Query query = session.createQuery(sql + Snapshot.class.getName());
        query.executeUpdate();
        query = session.createQuery(sql + Comment.class.getName());
        query.executeUpdate();
        query = session.createQuery(sql + Marker.class.getName());
        query.executeUpdate();
        query = session.createQuery(sql + User.class.getName());
        query.executeUpdate();
    }
}
