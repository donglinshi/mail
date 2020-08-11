package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.*;
import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.vo.*;
import com.cskaoyan.mail.model.vo.msg.*;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:33
 */
public class GoodsDaoImpl implements GoodsDao {

    public List<Type> getType() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> list = null;
        try {
            list = runner.query("select * from type",new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TypeGoodsVO> getGoodsByType(String typeId) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<TypeGoodsVO> voList = null;
        try {
            voList = runner.query("select id,img,name,price,typeId,stockNum from goods where typeId = ?",
                    new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voList;
    }

    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            //返回值标识影响的行数
            runner.update("insert into goods values (null,?,?,?,?,?,?)",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getLastInsertId() {

        //同线程下新插入的id，别的线程不会影响
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = (BigInteger) runner.query("select last_insert_id()",new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query.intValue();
    }

    public void addSpec(List<Spec> specList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specList) {
            try {
                runner.update("insert into spec values (null,?,?,?,?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public GoodsInfoVO getGoods(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        GoodsInfoVO goodsInfoVO = null;
        try {
            goodsInfoVO = runner.query("select id,img,name,`desc`,typeId from goods where id = ?",
                    new BeanHandler<GoodsInfoVO>(GoodsInfoVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsInfoVO;
    }

    public List<SpecInfoVO> getSpecs(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<SpecInfoVO> specInfoVOList = null;
        try {
            specInfoVOList = runner.query("select id,specName,stockNum,unitPrice from spec where goodsId = ?",
                    new BeanListHandler<SpecInfoVO>(SpecInfoVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specInfoVOList;
    }

    /**
     * 编辑商品时的添加规格函数
     * */
    public void addSpec(AddSpec addSpec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into spec values(null,?,?,?,?)",
                    addSpec.getSpecName(),
                    addSpec.getStockNum(),
                    addSpec.getUnitPrice(),
                    addSpec.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpec(DeleteSpecBO deleteSpecBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from spec where goodsId = ? and specName = ?",
                    deleteSpecBO.getGoodsId(),deleteSpecBO.getSpecName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update goods set img = ?,name = ?,price = ?,typeId = ?,stockNum = ?,`desc` = ? where id = ?",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc(),
                    goods.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSpec(List<UpdateSpecBO> specList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (UpdateSpecBO updateSpecBO : specList){
            try {
                runner.update("update spec set specName = ?,stockNum = ?,unitPrice = ? where id = ?",
                        updateSpecBO.getSpecName(),
                        updateSpecBO.getStockNum(),
                        updateSpecBO.getUnitPrice(),
                        updateSpecBO.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteGoods(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from goods where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpec(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from spec where goodsId = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addType(AddTypeBO addTypeBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into type values (null,?)",addTypeBO.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = (Long) runner.query("select count(*) from goods where typeId = ?", new ScalarHandler(), typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (query == 0){
            try {
                runner.update("delete from type where id = ?",typeId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return query == 0 ? 1 : 0;
    }

    public List<Message> noReplyMsg() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        List<Message> msg = null;
        try {
            msg = runner.query("select * from message where state = 1",new BeanListHandler<Message>(Message.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public GoodsMsg getGoodsMsg(Integer goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        GoodsMsg goods = null;
        try {
            goods = runner.query("select name from goods where id = ?",new BeanHandler<GoodsMsg>(GoodsMsg.class),goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public UserMsg getUserMsg(Integer userId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        UserMsg user = null;
        try {
            user = runner.query("select nickname as name from user where id = ? ",new BeanHandler<UserMsg>(UserMsg.class),userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Message> repliedMsg() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        List<Message> list = null;
        try {
            list = runner.query("select * from message where state = 0",new BeanListHandler<Message>(Message.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void reply(ReplyBO replyBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update message set replyContent = ?,state = ? where id = ?",replyBO.getContent(),0,replyBO.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SearchGoodsVO> searchGoods(String keyword) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        keyword = "%" + keyword +"%";
        List<SearchGoodsVO> list = null;
        try {
            list = runner.query("select id,img,name,price,typeId from goods where name like ?",
                    new BeanListHandler<SearchGoodsVO>(SearchGoodsVO.class),keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserGoodsInfoVO getGoodsInfo(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        UserGoodsInfoVO goodsInfoVO = null;
        try {
            goodsInfoVO = runner.query("select img,name,`desc`,typeId from goods where id = ?",new BeanHandler<UserGoodsInfoVO>(UserGoodsInfoVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsInfoVO;
    }

    public List<AskVO> getAskInfo(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<AskVO> askVOS = null;
        try {
            askVOS = runner.query("select id,content,userId,createtime as time from message where goodsId = ?",
                    new BeanListHandler<AskVO>(AskVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return askVOS;
    }

    public ReplyVO getReplyInfo(Integer id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        ReplyVO replyVO = null;
        try {
            replyVO = runner.query("select replyContent as content ,replytime as time from message where id = ?",
            new BeanHandler<ReplyVO>(ReplyVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return replyVO;
    }

    public List<Comment> getGoodsComments(String goodsId) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Comment> comments = null;
        try {
            comments = runner.query("select score, id, specName,comment,time,userId from comments where goodsId = ?",
                    new BeanListHandler<Comment>(Comment.class),goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public UserNickname getNickname(Integer userId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        UserNickname userNickname = null;
        try {
            userNickname = runner.query("select nickname from user where id = ?",new BeanHandler<UserNickname>(UserNickname.class),userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userNickname;
    }

    public GetUserIdByNameVO getUserIdByName(String token) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        GetUserIdByNameVO user = null;
        try {
            user = runner.query("select id from user where nickname = ? ",new BeanHandler<GetUserIdByNameVO>(GetUserIdByNameVO.class),token);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void askGoodsMsg(AskGoodsMsgBO askGoodsMsgBO, Integer id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Date time = new Date();
        try {
            runner.update("insert into message (userId,goodsId,content,state,createtime) values(?,?,?,?,?)",
                    id,
                    askGoodsMsgBO.getGoodsId(),
                    askGoodsMsgBO.getMsg(),
                    1,
                    new java.sql.Date(time.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
