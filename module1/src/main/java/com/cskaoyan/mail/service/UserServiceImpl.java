package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.UserDao;
import com.cskaoyan.mail.dao.UserDaoImpl;
import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.vo.UserInfoVO;
import com.cskaoyan.mail.model.vo.UserName;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/6 16:14
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public List<UserInfoVO> allUser() {
        return userDao.allUser();
    }

    public List<UserInfoVO> searchUser(String word) {
        return userDao.searchUser(word);
    }

    public int deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    public int signUp(User user) {
        return userDao.signUp(user);
    }

    public int login(AdminLoginBO user) {
        return userDao.login(user);
    }

    public UserName searchName(String email, String pwd) {
        return userDao.searchName(email,pwd);
    }
}
