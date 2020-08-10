package com.cskaoyan.mail.controller.MallServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;
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
 * @date 2020/8/10 17:27
 */
@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index/", "");

        if ("getType".equals(action)){
            getType(request,response);
        }
    }

    /**
     * @description:显示品类
     * @params:
     * @author: 史栋林
     */
    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> types = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(types)));
    }
}
