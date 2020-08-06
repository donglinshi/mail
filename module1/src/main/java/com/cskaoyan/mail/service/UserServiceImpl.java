package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.UserDao;
import com.cskaoyan.mail.dao.UserDaoImpl;
import com.cskaoyan.mail.model.User;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/6 16:14
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public List<User> allUser() {
        return userDao.allUser();
    }

    public List<User> searchUser(String word) {
        return userDao.searchUser(word);
    }
}
