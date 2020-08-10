package com.cskaoyan.mail.controller.AdminServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.AddSpec;
import com.cskaoyan.mail.model.vo.GoodsAndSpecInfoVO;
import com.cskaoyan.mail.model.vo.GoodsInfoVO;
import com.cskaoyan.mail.model.vo.SpecInfoVO;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;
import com.cskaoyan.mail.model.vo.msg.NoReplyMsgVO;
import com.cskaoyan.mail.model.vo.msg.RepliedMsgVO;
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
        }else if ("addSpec".equals(action)){
            addSpec(request,response);
        }else if ("deleteSpec".equals(action)){
            deleteSpec(request,response);
        }else if ("updateGoods".equals(action)){
            updateGoods(request,response);
        }else if ("addType".equals(action)){
            addType(request,response);
        }else if ("reply".equals(action)){
            reply(request,response);
        }
    }

    /**
     * @description:回复留言
     * @params:
     * @author: 史栋林
     */
    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requestBody,ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:添加类目
     * @params:
     * @author: 史栋林
     */
    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AddTypeBO addTypeBO = gson.fromJson(requestBody, AddTypeBO.class);
        goodsService.addType(addTypeBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:编辑商品
     * @params:
     * @author: 史栋林
     */
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        UpdateGoodsBO updateGoodsBO = gson.fromJson(requestBody, UpdateGoodsBO.class);
        goodsService.updateGoods(updateGoodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:在编辑商品时删除规格
     * @params:
     * @author: 史栋林
     */
    private void deleteSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        DeleteSpecBO deleteSpecBO = gson.fromJson(requestBody,DeleteSpecBO.class);
        goodsService.deleteSpec(deleteSpecBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:在编辑商品时添加规格
     * @params:
     * @author: 史栋林
     */
    private void addSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        AddSpec addSpec  = gson.fromJson(requestBody, AddSpec.class);
        goodsService.addSpec(addSpec);
        response.getWriter().println(gson.toJson(Result.ok(addSpec)));
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
        }else if ("getGoodsInfo".equals(action)){
            getGoodsInfo(request,response);
        }else if ("deleteGoods".equals(action)){
            deleteGoods(request,response);
        }else if ("deleteType".equals(action)){
            deleteType(request,response);
        }else if ("noReplyMsg".equals(action)){
            noReplyMsg(request,response);
        }else if ("repliedMsg".equals(action)){
            repliedMsg(request,response);
        }

    }

    /**
     * @description:查询已回复留言列表
     * @params:
     * @author: 史栋林
     */
    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<RepliedMsgVO> list = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(list)));
    }

    /**
     * @description:查询未回复留言列表
     * @params:
     * @author: 史栋林
     */
    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<NoReplyMsgVO> noReplyMsgVOS = goodsService.noReplyMsg();
        response.getWriter().println(gson.toJson(Result.ok(noReplyMsgVOS)));
    }

    /**
     * @description:删除当前目类，现在只实现了目类下商品为空时可以删除
     * @params:
     * @author: 史栋林
     */
    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        int code = goodsService.deleteType(typeId);
        if (code == 0){
            response.getWriter().println(gson.toJson(Result.error("当前目类之下存在商品，不可删除！")));
        }
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:删除商品信息
     * @params:
     * @author: 史栋林
     */
    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        goodsService.deleteGoods(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:获取所编辑商品的信息
     * 1.获取goods表中的数据
     * 2.获取spec表中的数据
     * 3.包装数据
     * @params:
     * @author: 史栋林
     */
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //首先获取参数
        String id = request.getParameter("id");
        //获取goods的相应字段
        GoodsInfoVO goodsInfoVO = goodsService.getGoods(id);
        //获取指定的spec表中的相应字段
        List<SpecInfoVO> specInfoVOList = goodsService.getSpecs(id);
        //拼接出接口想要的数据类型，之后再转化为json对象
        GoodsAndSpecInfoVO goodsAndSpecInfoVO = new GoodsAndSpecInfoVO(goodsInfoVO,specInfoVOList);
        response.getWriter().println(gson.toJson(Result.ok(goodsAndSpecInfoVO)));
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
