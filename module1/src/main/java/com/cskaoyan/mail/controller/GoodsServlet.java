package com.cskaoyan.mail.controller;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.AddGoodsBO;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;
import com.cskaoyan.mail.service.GoodsService;
import com.cskaoyan.mail.service.GoodsServiceImpl;
import com.cskaoyan.mail.utils.FileUploadUtils;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;

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
 * @date 2020/8/7 11:15
 */
@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("imgUpload".equals(action)){
            imgUpload(request,response);
        }else if ("addGoods".equals(action)){
            addGoods(request,response);
        }

    }


    /**
     * @description:添加新商品
     * 1.获取请求体参数
     * 2.处理业务逻辑
     * 3.做出响应
     * @params:
     * @author: 史栋林
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        AddGoodsBO addGoodsBO = gson.fromJson(requestBody, AddGoodsBO.class);
        goodsService.addGoods(addGoodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:上传新添加商品的图片
     * @params:
     * @author: 史栋林
     * commons-fileUpload
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //借助工具类处理请求体中的数据
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        //获取文件的路径
        String filePath = (String) map.get("file");
        //涉及跨域获取文件信息的问题，所以需要全路径，
        String domain = (String) getServletContext().getAttribute("domain");
        response.getWriter().println(gson.toJson(Result.ok(domain + filePath)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println("doget");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("getType".equals(action)){
            getType(request,response);
        }else if ("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }

    }

    /**
     * @description:获取指定类目的商品
     * @params:
     * @author: 史栋林
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取类目id
        String typeId = request.getParameter("typeId");
        //校验参数 可无
        List<TypeGoodsVO> voList = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(voList)));
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
