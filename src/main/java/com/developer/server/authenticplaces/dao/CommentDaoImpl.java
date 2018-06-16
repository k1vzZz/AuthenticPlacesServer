package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Comment;
import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public class CommentDaoImpl implements CommentDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addComment(String text, Timestamp time, User author, Marker marker){
        Session session = sessionFactory.getCurrentSession();
        Comment comment = new Comment(text, time, author, marker);
        session.saveOrUpdate(comment);
    }
}
