package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/6 16:17
 */
public class UserDaoImpl implements UserDao {

    public List<User> allUser() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> list = null;
        try {
            list = runner.query("select * from user",new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> searchUser(String word) {

        word = "%" + word + "%";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> list = null;
        try {
            list = runner.query("select * from user where nickname like ?",new BeanListHandler<User>(User.class),word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
