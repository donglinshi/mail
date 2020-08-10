package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.vo.UserInfoVO;
import com.cskaoyan.mail.model.vo.UserName;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/6 16:17
 */
public interface UserDao {

    List<UserInfoVO> allUser();

    List<UserInfoVO> searchUser(String word);

    int deleteUser(String id);

    int signUp(User user);

    int login(AdminLoginBO user);

    UserName searchName(String email, String pwd);
}
