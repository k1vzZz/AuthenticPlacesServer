package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByIdentifierGoogle(String identifierClient){
        Session session = sessionFactory.getCurrentSession();
        String sql = "from User where identifierClient=:identifier";
        Query query = session.createQuery(sql);
        query.setParameter("identifier", identifierClient);
        List<?> list = query.list();
        return list != null && list.size() > 0 ? (User) list.get(0) : null;
    }

    @Override
    @Transactional
    public User addUser(String identifierClient, String login, String urlImage) {
        User user = new User(identifierClient, login, urlImage);
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        return user;
    }
}
