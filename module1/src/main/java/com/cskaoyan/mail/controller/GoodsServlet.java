package com.cskaoyan.mail.controller;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.service.GoodsService;
import com.cskaoyan.mail.service.GoodsServiceImpl;
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
 * @date 2020/8/7 11:15
 */
@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doget");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("getType".equals(action)){
            getType(request,response);
        }

    }

    /**
     * @description:获取商品的种类
     * @params:
     * @author: 史栋林
     */
    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> list = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(list)));
    }
}
