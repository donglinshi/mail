package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.Admin;
import com.cskaoyan.mail.model.bo.AdminAddAdminBO;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.bo.UpdateAdmin;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/5 20:14
 */
public interface AdminDao {

    int login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    int addAdmins(AdminAddAdminBO adminAddAdminBO);

    Admin getAdminInfo(Integer id);

    int updateAdmin(UpdateAdmin updateAdmin);

    int deleteAdmin(Integer id);
}
