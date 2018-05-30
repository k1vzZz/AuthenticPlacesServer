package com.developer.server.authenticplaces.AuthenticPlacesServer.service;

import com.developer.server.authenticplaces.AuthenticPlacesServer.dao.TestDao;
import com.developer.server.authenticplaces.AuthenticPlacesServer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {

    @Autowired
    TestDao testDao;

    public User show(){
        testDao.testInsert();
        return testDao.testSelect();
    }
}
