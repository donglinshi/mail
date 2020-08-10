package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.vo.UserInfoVO;
import com.cskaoyan.mail.model.vo.UserName;
import com.cskaoyan.mail.model.vo.UserReturnVO;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/6 16:17
 */
public class UserDaoImpl implements UserDao {

    public List<UserInfoVO> allUser() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<UserInfoVO> list = null;
        try {
            list = runner.query("select id,email,nickname,recipient,address,phone from user",new BeanListHandler<UserInfoVO>(UserInfoVO.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<UserInfoVO> searchUser(String word) {

        word = "%" + word + "%";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<UserInfoVO> list = null;
        try {
            list = runner.query("select id,email,nickname,recipient,address,phone from user where nickname like ?",new BeanListHandler<UserInfoVO>(UserInfoVO.class),word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteUser(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer update = null;
        try {
            update = runner.update("delete from user where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update != 0 ? 1: 0;
    }

    public int signUp(User user) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from user where email = ?",new ScalarHandler(),user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (query != 0){
            return 403;
        }

        Integer update = null;
        try {
            update = runner.update("insert into user values(?,?,?,?,?,?,?)",null,
                    user.getEmail(),
                    user.getNickname(),
                    user.getPwd(),
                    user.getRecipient(),
                    user.getAddress(),
                    user.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }

        return update != 0 ? 200 : 404;
    }

    public int login(AdminLoginBO user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from user where email = ? and pwd = ?",new ScalarHandler(),user.getEmail(),user.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query != null ? 200 : 404;
    }

    public UserName searchName(String email, String pwd) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        UserName userName = null;
        try {
            userName = runner.query("select nickname from user where email = ? and pwd = ?",new BeanHandler<UserName>(UserName.class),email,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }
}
