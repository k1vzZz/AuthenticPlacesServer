package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.Marker;
import com.developer.server.authenticplaces.entity.User;

import java.sql.Timestamp;

public interface CommentDao {
    void addComment(String text, Timestamp time, User author, Marker marker);
}
