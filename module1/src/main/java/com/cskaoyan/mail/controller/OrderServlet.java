package com.cskaoyan.mail.controller;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.bo.ChangeOrderBO;
import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;
import com.cskaoyan.mail.model.vo.orderbyid.OrderByIdVO;
import com.cskaoyan.mail.service.OrderService;
import com.cskaoyan.mail.service.OrderServiceImpl;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 11:27
 */
@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {

    private Gson gson = new Gson();

    private OrderService orderService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");

        if ("ordersByPage".equals(action)){
            ordersByPage(request,response);
        }else if ("changeOrder".equals(action)){
            changeOrder(request,response);
        }

    }

    /**
     * @description:提交改变
     * @params:
     * @author: 史栋林
     */
    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        ChangeOrderBO changeOrderBO = gson.fromJson(requestBody, ChangeOrderBO.class);
        //暂时不校验
        orderService.changeOrder(changeOrderBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description: 分页显示订单信息，依靠不同参数，多条件模糊查询
     * 1.获取请求参数
     * 2.业务逻辑处理
     * 3.响应
     * @params:
     * @author: 史栋林
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        PageOrderBO pageOrderBO = null;
        try{
            pageOrderBO = gson.fromJson(requestBody, PageOrderBO.class);
            if (!StringUtils.isEmpty(pageOrderBO.getMoneyLimit1())){
                //金额的上线和下线可以没有，但是有的话必须是数字
                Double.parseDouble(pageOrderBO.getMoneyLimit1());
            }
            if (!StringUtils.isEmpty(pageOrderBO.getMoneyLimit2())){
                Double.parseDouble(pageOrderBO.getMoneyLimit2());
            }
        }catch (Exception e){
            response.getWriter().println(gson.toJson(Result.error("参数类型不匹配！")));
            return;
        }
        Map<String,Object> result = orderService.ordersByPage(pageOrderBO);
        response.getWriter().println(gson.toJson(Result.ok(result)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");
        if("order".equals(action)){
            order(request,response);
        }else if ("deleteOrder".equals(action)){
            deleteOrder(request,response);
        }

    }

    /**
     * @description:删除订单信息
     * @params:
     * @author: 史栋林
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:获取所编辑订单的详细信息
     * @params:
     * @author: 史栋林
     */
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");

        Map<String,Object> result = orderService.order(id);

        response.getWriter().println(gson.toJson(Result.ok(result)));
    }


}
