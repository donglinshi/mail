package com.cskaoyan.mail.controller;

import com.cskaoyan.mail.model.Admin;
import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.bo.AdminAddAdminBO;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.bo.UpdateAdmin;
import com.cskaoyan.mail.model.vo.AdminLoginVO;
import com.cskaoyan.mail.service.AdminService;
import com.cskaoyan.mail.service.AdminServiceImp1;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/5 18:20
 */
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private Gson gson = new Gson();
    private AdminService adminService = new AdminServiceImp1();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dopost");
        // 获取到 /api/admin/admin/login
        String requestURI = request.getRequestURI();
        //获取想要的路径字段
        String action = requestURI.replace("/api/admin/admin/", "");
        if ("login".equals(action)){
            login(request,response);
        }else if ("addAdminss".equals(action)){
            addAdmins(request,response);
        }else if ("updateAdminss".equals(action)){
            updateAdmin(request,response);
        }

    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求体
        String requestBody = HttpUtils.getRequestBody(request);
        //借用添加的BO可以完成
        UpdateAdmin updateAdmin = gson.fromJson(requestBody,UpdateAdmin.class);
        //具体实现修改
        int code = adminService.updateAdmin(updateAdmin);
        if (code == 0){
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("修改出错!")));
    }

    /**
     * @description:添加新的管理员信息
     * @params:
     * @return:
     * @author: 史栋林
     */
    private void addAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminAddAdminBO adminAddAdminBO = gson.fromJson(requestBody,AdminAddAdminBO.class);
        //可以不校验参数为空
        //if (StringUtils.isEmpty(adminAddAdminBO.getEmail()) || StringUtils.isEmpty(adminAddAdminBO.getNickname())
        // ||StringUtils.isEmpty(adminAddAdminBO.getPwd())){
        // response.getWriter().println(gson.toJson(Result.error("参数不可为空")));
        // }
        int code = adminService.addAdmins(adminAddAdminBO);
        if (code == 0){
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("添加失败！")));
    }

    /**
     * @description:管理员登录逻辑
     * @params: request,response
     * @author: 史栋林
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);
        //判断是否为空,StringUtils
        if (StringUtils.isEmpty(loginBO.getEmail()) || StringUtils.isEmpty(loginBO.getPwd())){
            //返回响应结果
            response.getWriter().println(gson.toJson(Result.error("参数不能为空！")));
            return;
        }
        //总结MVC框架的划分
        int code = adminService.login(loginBO);
        //返回响应结果
        if (code == 200){
            response.getWriter().println(gson.toJson(Result.ok(new AdminLoginVO(loginBO.getEmail(),loginBO.getEmail()))));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("请确认用户名和密码")));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("doget");
        // 获取到 /api/admin/admin/login
        String requestURI = request.getRequestURI();
        //获取想要的路径字段
        String action = requestURI.replace("/api/admin/admin/", "");
        if ("allAdmins".equals(action)){
            allAdmins(request,response);
        }else if ("getAdminsInfo".equals(action)){
            getAdminsInfo(request,response);
        }else if ("deleteAdmins".equals(action)){
            deleteAdmin(request,response);
        }

    }

    /**
     * @description:删除管理员账户
     * @params:
     * @return:
     * @author: 史栋林
     */
    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取指定的参数
        Integer id = Integer.parseInt(request.getParameter("id"));
        int code = adminService.deleteAdmin(id);
        if (code == 0){
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("删除失败！")));
    }

    /**
     * @description:获取指定的修改信息
     * @params:
     * @return:
     * @author: 史栋林
     */
    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取指定的参数
        Integer id = Integer.parseInt(request.getParameter("id"));
        Admin admin = adminService.getAdminInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(admin)));
    }

    /**
     * @description:返回所有的管理员信息
     * @params:
     * @return::
     * @author: 史栋林
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();

        response.getWriter().println(gson.toJson(Result.ok(adminList)));
    }
}