package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.UserDao;
import com.cskaoyan.mail.dao.UserDaoImpl;
import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.model.UserModify;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.bo.UserInfoBO;
import com.cskaoyan.mail.model.bo.UserUpdatePwdBO;
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

    public UserModify getData(String token) {
        UserInfoVO user = userDao.getData(token);
        return new UserModify(0,user.getId(),user.getEmail(),user.getNickname(),
                user.getRecipient(),user.getAddress(),user.getPhone());
    }

    public int updateUserPwd(UserUpdatePwdBO userUpdatePwdBO) {
        int code = userDao.getPwdInfo(userUpdatePwdBO.getId(),userUpdatePwdBO.getOldPwd());
        if (code == 0){
            return code;
        }
        //更改密码
        userDao.updatePwd(userUpdatePwdBO);
        return 1;
    }

    public void updateData(UserInfoBO userInfoBO) {
        userDao.updateData(userInfoBO);
    }
}
