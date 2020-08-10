package com.cskaoyan.mail.controller.MallServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.vo.SearchGoodsVO;
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
 * @date 2020/8/10 19:10
 */
@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    private Gson gson = new Gson();
    private GoodsService goodsService = new GoodsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/", "");

        if ("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }else if ("searchGoods".equals(action)){
            searchGoods(request,response);
        }

    }

    /**
     * @description:
     * @params:
     * @author: 史栋林
     */
    private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");

        List<SearchGoodsVO> list = goodsService.searchGoods(keyword);
        response.getWriter().println(gson.toJson(Result.ok(list)));
    }

    /**
     * @description:显示品类商品
     * @params:
     * @author: 史栋林
     */
    private void getGoodsByType(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> list = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(list)));
    }
}
