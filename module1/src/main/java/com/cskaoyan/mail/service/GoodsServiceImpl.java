package com.cskaoyan.mail.service;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cskaoyan.mail.dao.GoodsDao;
import com.cskaoyan.mail.dao.GoodsDaoImpl;
import com.cskaoyan.mail.model.*;
import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.vo.*;
import com.cskaoyan.mail.model.vo.msg.*;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:25
 */
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao = new GoodsDaoImpl();

    public List<Type> getType() {
        return goodsDao.getType();
    }

    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    /**
     * 新增商品表price,stockNum需要通过specList运算得到
     * 1.保存数据到商品表
     * 2.拿到 商品表刚刚插入的id
     * 3.将id以及spec数据保存到规格表
     * */
    public void addGoods(AddGoodsBO addGoodsBO) {
        //处理商品的价格和库存，在商品表中存储的是不同规格的最低价和最大库存
        List<SpecBO> specBOList = addGoodsBO.getSpecList();
        double price = specBOList.get(0).getUnitPrice();
        int stockNum = specBOList.get(0).getStockNum();
        //找到最低价
        for (int i = 1; i < specBOList.size(); i++){
            if (price > specBOList.get(i).getUnitPrice()){
                price = specBOList.get(i).getUnitPrice();
            }
        }
        //获取库存最大值
        for (int i = 1; i < specBOList.size(); i++){
            if (stockNum < specBOList.get(i).getStockNum()){
                stockNum = specBOList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(null,addGoodsBO.getImg(),addGoodsBO.getName(),
                price,addGoodsBO.getTypeId(),stockNum,addGoodsBO.getDesc());

        goodsDao.addGoods(goods);
        //获取最后一次插入的id，用于spec表的更新
        int id = goodsDao.getLastInsertId();
        //保存数据到规格表,添加的可能是多规格的商品
        List<Spec> specList = new ArrayList<Spec>();
        for (SpecBO specBO : specBOList) {
            Spec spec = new Spec(null,
                    specBO.getSpecName(),
                    specBO.getStockNum(),
                    specBO.getUnitPrice(),
                    id);

            specList.add(spec);
        }

        goodsDao.addSpec(specList);
    }

    public GoodsInfoVO getGoods(String id) {
        return goodsDao.getGoods(id);
    }

    public List<SpecInfoVO> getSpecs(String id) {
        return goodsDao.getSpecs(id);
    }

    public void addSpec(AddSpec addSpec) {
        goodsDao.addSpec(addSpec);
    }

    public void deleteSpec(DeleteSpecBO deleteSpecBO) {
        goodsDao.deleteSpec(deleteSpecBO);
    }

    /**
     * 更新goods、spec表
     * */
    public void updateGoods(UpdateGoodsBO updateGoodsBO) {
        //虽然代码重复了，但是先不优化
        List<UpdateSpecBO> specList = updateGoodsBO.getSpecList();

        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();

        //找到价格的最小值
        for (int i = 1; i < specList.size(); i++){
            if (price > specList.get(i).getUnitPrice()){
                price = specList.get(i).getUnitPrice();
            }
        }
        //找到库存的最大值
        for (int i = 1; i < specList.size(); i++){
            if (stockNum < specList.get(i).getStockNum()){
                stockNum = specList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(updateGoodsBO.getId(),
                updateGoodsBO.getImg(),
                updateGoodsBO.getName(),
                price,
                updateGoodsBO.getTypeId(),
                stockNum,
                updateGoodsBO.getDesc());
        goodsDao.updateGoods(goods);

        //处理spec表,不用继续像添加一样的封装
        goodsDao.updateSpec(specList);
    }

    public void deleteGoods(String id) {
        goodsDao.deleteGoods(id);
        goodsDao.deleteSpec(id);
    }

    public void addType(AddTypeBO addTypeBO) {
        goodsDao.addType(addTypeBO);
    }

    public int deleteType(String typeId) {
        return goodsDao.deleteType(typeId);
    }

    public List<NoReplyMsgVO> noReplyMsg() {

        List<Message> msg = goodsDao.noReplyMsg();
        //结果集
        List<NoReplyMsgVO> list = new ArrayList<NoReplyMsgVO>();
        //时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < msg.size(); i++){
            //对属性进行再封装
            Integer goodsId = msg.get(i).getGoodsId();
            Integer userId = msg.get(i).getUserId();
            NoReplyMsgVO temp = new NoReplyMsgVO(msg.get(i).getId(),
                    msg.get(i).getUserId(),
                    msg.get(i).getGoodsId(),
                    msg.get(i).getContent(),
                    msg.get(i).getState(),
                    simpleDateFormat.format(msg.get(i).getCreatetime()),
                    goodsDao.getGoodsMsg(goodsId),
                    goodsDao.getUserMsg(userId));
            list.add(temp);
        }
        return list;
    }

    public List<RepliedMsgVO> repliedMsg() {

        List<Message> msg = goodsDao.repliedMsg();

        List<RepliedMsgVO> list = new ArrayList<RepliedMsgVO>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < msg.size(); i++){
            RepliedMsgVO temp = new RepliedMsgVO(msg.get(i).getId(),
                    msg.get(i).getUserId(),
                    msg.get(i).getGoodsId(),
                    msg.get(i).getContent(),
                    msg.get(i).getReplyContent(),
                    msg.get(i).getState(),
                    simpleDateFormat.format(msg.get(i).getCreatetime()),
                    goodsDao.getGoodsMsg(msg.get(i).getGoodsId()),
                    goodsDao.getUserMsg(msg.get(i).getUserId()));
            list.add(temp);
        }
        return list;
    }

    public void reply(ReplyBO replyBO) {
        goodsDao.reply(replyBO);
    }

    public List<SearchGoodsVO> searchGoods(String keyword) {
        return goodsDao.searchGoods(keyword);
    }

    public UserGoodsInfoVO getGoodsInfo(String id) {
        return goodsDao.getGoodsInfo(id);
    }

    public List<GetMsgInfo> getGoodsMsg(String id) {
        List<GetMsgInfo> list = new ArrayList<GetMsgInfo>();
        List<AskVO> askVOS = goodsDao.getAskInfo(id);

        for (AskVO ask : askVOS){
            UserMsg userMsg = goodsDao.getUserMsg(ask.getUserId());
            ReplyVO reply = goodsDao.getReplyInfo(ask.getId());
            list.add(new GetMsgInfo(ask.getId(),ask.getContent(),userMsg.getName(),ask.getTime(),reply));
        }
        return list;
    }

    public List<CommentList> getGoodsComments(String goodsId) {

        List<CommentList> commentLists = new ArrayList<CommentList>();

        List<Comment> comments = goodsDao.getGoodsComments(goodsId);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Comment comment : comments){
            UserNickname userNickname = goodsDao.getNickname(comment.getUserId());
            commentLists.add(new CommentList(userNickname,
                    comment.getScore(),
                    comment.getId(),
                    comment.getSpecName(),
                    comment.getComment(),
                    format1.format(comment.getTime()),
                    comment.getUserId()));
        }
        return commentLists;
    }

    public void askGoodsMsg(AskGoodsMsgBO askGoodsMsgBO) {
        GetUserIdByNameVO userId = goodsDao.getUserIdByName(askGoodsMsgBO.getToken());

        goodsDao.askGoodsMsg(askGoodsMsgBO,userId.getId());

    }


}
