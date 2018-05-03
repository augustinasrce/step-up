package com.haka.stepup.services;

import com.haka.stepup.db.User;
import com.haka.stepup.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@WebService
@Component
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public void create(User user) {
        userDao.create(user);
    }

    public User findUser(long id){
        return userDao.getUserById(id);
    }

    public List<User> findAll(){
        return userDao.getAllUsers();
    }
}
