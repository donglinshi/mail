package com.cskaoyan.mail.controller.MallServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.bo.AskGoodsMsgBO;
import com.cskaoyan.mail.model.vo.*;
import com.cskaoyan.mail.model.vo.msg.*;
import com.cskaoyan.mail.service.GoodsService;
import com.cskaoyan.mail.service.GoodsServiceImpl;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/", "");
        if ("askGoodsMsg".equals(action)){
            askGoodsMsg(request,response);
        }

    }

    /**
     * @description:发送提问
     * @params:
     * @author: 史栋林
     */
    private void askGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AskGoodsMsgBO askGoodsMsgBO = gson.fromJson(requestBody, AskGoodsMsgBO.class);
        goodsService.askGoodsMsg(askGoodsMsgBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/", "");

        if ("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }else if ("searchGoods".equals(action)){
            searchGoods(request,response);
        }else if ("getGoodsInfo".equals(action)){
            getUserGoodsInfo(request,response);
        }else if ("getGoodsMsg".equals(action)){
            getGoodsMsg(request,response);
        }else if ("getGoodsComment".equals(action)){
            getGoodsComments(request,response);
        }

    }


    /**
     * @description:获取用户评论
     * @params:
     * @author: 史栋林
     */
    private void getGoodsComments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsId = request.getParameter("goodsId");
        //获取ComentList的相关信息
        List<CommentList> lists = goodsService.getGoodsComments(goodsId);
        Double score = 0.0;
        Integer num = 0;
        //计算分数的大小
        if (!lists.isEmpty()){
            for (CommentList commentList : lists){
                if (commentList.getScore() != 0 ){
                    score += commentList.getScore();
                    num++;
                }
            }
            if (num != 0){
                CommentsVO commentsVO = new CommentsVO(lists,score / num);
                response.getWriter().println(gson.toJson(Result.ok(commentsVO)));
                return;
            }
        }
        //评价为空或评分为0
        response.getWriter().println(gson.toJson(Result.ok(new CommentsVO(lists))));
    }

    /**
     * @description:获取问答信息
     * @params:
     * @author: 史栋林
     */
    private void getGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //商品的id
        String id = request.getParameter("id");

        List<GetMsgInfo> msg = goodsService.getGoodsMsg(id);

        response.getWriter().println(gson.toJson(Result.ok(msg)));
    }

    /**
     * @description:获取商品你的详细信息
     * @params:
     * @author: 史栋林
     */
    private void getUserGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //应该再service封装数据的，暂时不改了
        String id = request.getParameter("id");

        UserGoodsInfoVO userGoodsInfoVO = goodsService.getGoodsInfo(id);
        //获取指定商品的规格信息
        List<SpecInfoVO> list = goodsService.getSpecs(id);
        UserGoodsAndSpecVO userGoodsAndSpecVO = new UserGoodsAndSpecVO(userGoodsInfoVO.getImg(),
                userGoodsInfoVO.getName(),
                userGoodsInfoVO.getDesc(),
                userGoodsInfoVO.getTypeId(),
                list);

        response.getWriter().println(gson.toJson(Result.ok(userGoodsAndSpecVO)));
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
