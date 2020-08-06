package com.cskaoyan.mail.controller;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.service.UserService;
import com.cskaoyan.mail.service.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/6 15:52
 */
@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {

    private Gson gson = new Gson();

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doget");
        ///api/admin/user/allUser
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if ("allUser".equals(action)){
            allUser(request,response);
        }

    }

    /**
     * @description: 显示所有的用户
     * @params:
     * @author: 史栋林
     */
    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> list = userService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(list)));
    }
}