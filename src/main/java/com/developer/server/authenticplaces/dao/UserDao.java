package com.developer.server.authenticplaces.dao;

import com.developer.server.authenticplaces.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {

    User getUserByIdentifierGoogle(String identifierClient);

    User addUser(String identifierClient, String login, String urlImage);
}
