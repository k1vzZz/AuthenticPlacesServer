package com.developer.server.authenticplaces.AuthenticPlacesServer.dao;

import com.developer.server.authenticplaces.AuthenticPlacesServer.entity.User;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void testInsert(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setLogin("lucky");
        user.setId(1);
        session.replicate(user, ReplicationMode.OVERWRITE);
        transaction.commit();
        session.close();
    }

    public User testSelect(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user;
        user = session.get(User.class, 1);
        transaction.commit();
        session.close();
        return user;
    }
}
