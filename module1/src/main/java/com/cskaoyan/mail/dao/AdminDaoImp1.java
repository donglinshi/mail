package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.Admin;
import com.cskaoyan.mail.model.bo.AdminAddAdminBO;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.bo.UpdateAdmin;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/5 20:14
 */
public class AdminDaoImp1 implements AdminDao {
    public int login(AdminLoginBO loginBO) {
        //JDBC的操作
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
             query = (Long) runner.query("select count(id) from admin where email = ? and pwd = ?",
                    new ScalarHandler(),loginBO.getEmail(),loginBO.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }

        return query !=0 ? 200 : 404;
    }

    public List<Admin> allAdmins() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin",new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public int addAdmins(AdminAddAdminBO adminAddAdminBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer update = null;
        try {
            update = runner.update("insert into admin (email,pwd,nickname) values (?,?,?)",
                    adminAddAdminBO.getEmail(),adminAddAdminBO.getPwd(),adminAddAdminBO.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update != 0 ? 0 : 1;
    }

    public Admin getAdminInfo(Integer id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select * from admin where id = ?",new BeanHandler<Admin>(Admin.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public int updateAdmin(UpdateAdmin updateAdmin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer update = null;
        try {
            update = runner.update("update admin set email = ? ,pwd = ? ,nickname = ? where id = ?",
                    updateAdmin.getEmail(),updateAdmin.getPwd(),updateAdmin.getNickname(),updateAdmin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update != 0 ? 0 : 1;
    }

    public int deleteAdmin(Integer id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer update = null;
        try {
            update = runner.update("delete from admin where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update != 0 ? 0 : 1;
    }
}
