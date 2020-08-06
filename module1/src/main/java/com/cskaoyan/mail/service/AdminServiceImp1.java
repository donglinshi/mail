package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.AdminDao;
import com.cskaoyan.mail.dao.AdminDaoImp1;
import com.cskaoyan.mail.model.Admin;
import com.cskaoyan.mail.model.bo.AdminAddAdminBO;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.bo.SearchAdminBO;
import com.cskaoyan.mail.model.bo.UpdateAdmin;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/5 20:07
 */
public class AdminServiceImp1 implements AdminService {

    private AdminDao adminDao = new AdminDaoImp1();

    public int login(AdminLoginBO loginBO) {
        return adminDao.login(loginBO);
    }

    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }

    public int addAdmins(AdminAddAdminBO adminAddAdminBO) {
        return adminDao.addAdmins(adminAddAdminBO);
    }

    public Admin getAdminInfo(Integer id) {
        return adminDao.getAdminInfo(id);
    }

    public int updateAdmin(UpdateAdmin updateAdmin) {
        return adminDao.updateAdmin(updateAdmin);
    }

    public int deleteAdmin(Integer id) {
        return adminDao.deleteAdmin(id);
    }

    public List<Admin> searchAdmins(SearchAdminBO searchAdminBo) {
        return adminDao.searchAdmins(searchAdminBo);
    }

}
