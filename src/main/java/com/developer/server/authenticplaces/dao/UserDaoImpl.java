package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public User getUserById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Transactional
    public void addUser(Integer identifierClient, String login, String urlImage) {
        User user = new User(identifierClient, login, urlImage);
        String url = "insert ";
    }
}
