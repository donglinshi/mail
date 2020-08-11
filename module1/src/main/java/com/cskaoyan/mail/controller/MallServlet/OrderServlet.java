package com.cskaoyan.mail.controller.MallServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.bo.ShoppingCartBO;
import com.cskaoyan.mail.service.OrderService;
import com.cskaoyan.mail.service.OrderServiceImpl;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

/**
 * @author 史栋林
 * @date 2020/8/11 18:03
 */
@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");

        if ("addOrder".equals(action)){
            //加入购物车
            addOrder(request,response);
        }
    }

    /**
     * @description:添加入购物车
     * @params:
     * @author: 史栋林
     */
    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        ShoppingCartBO shoppingCartBO = gson.fromJson(requestBody, ShoppingCartBO.class);
        int code = orderService.addOrder(shoppingCartBO);
        if (code == -1){
            response.getWriter().println(gson.toJson(Result.error("库存不足！")));
        }else if(code == 1){
            response.getWriter().println(gson.toJson(Result.ok()));
        }else {
            response.getWriter().println(gson.toJson(Result.error("添加购物车失败！")));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");
    }
}
