package com.cskaoyan.mail.controller.MallServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.bo.SendCommentBO;
import com.cskaoyan.mail.model.bo.SettleAccountsBO;
import com.cskaoyan.mail.model.bo.SettleBO;
import com.cskaoyan.mail.model.bo.ShoppingCartBO;
import com.cskaoyan.mail.model.vo.GetOrderByStateVO;
import com.cskaoyan.mail.service.OrderService;
import com.cskaoyan.mail.service.OrderServiceImpl;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        }else if ("settleAccounts".equals(action)){
            settleAccounts(request,response);
        }else if ("sendComment".equals(action)){
            sendComment(request,response);
        }
    }

    /**
     * @description:用户对商品的评价
     * @params:
     * @author: 史栋林
     */
    private void sendComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        SendCommentBO sendCommentBO = gson.fromJson(requestBody,SendCommentBO.class);

        orderService.sendComment(sendCommentBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:购物车下单
     * @params:
     * @author: 史栋林
     */
    private void settleAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        //获取对象数组型json对象
//        Type type = new TypeToken<List<SettleAccountsBO>>() {}.getType();
        SettleBO list = gson.fromJson(requestBody,SettleBO.class);

        for (SettleAccountsBO account : list.getCartList()){
            orderService.settleAccounts(account);
        }
        response.getWriter().println(gson.toJson(Result.ok()));
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
        if ("getOrderByState".equals(action)){
            getOrderByState(request,response);
        }else if ("confirmReceive".equals(action)){
            confirmReceive(request,response);
        }else if ("pay".equals(action)){
            pay(request,response);
        }else if ("deleteOrder".equals(action)){
            deleteOrder(request,response);
        }
    }

    /**
     * @description:删除订单
     * @params:
     * @author: 史栋林
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:确认付款
     * @params:
     * @author: 史栋林
     */
    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        orderService.confirmPay(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:确认收货
     * @params:
     * @author: 史栋林
     */
    private void confirmReceive(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        orderService.confiemRe(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:根据状态获取订单
     * @params:
     * @author: 史栋林
     */
    private void getOrderByState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        String token = request.getParameter("token");

        List<GetOrderByStateVO> orders = orderService.getOrderByState(state,token);
        response.getWriter().println(gson.toJson(Result.ok(orders)));
    }
}
