package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.*;
import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.vo.*;
import com.cskaoyan.mail.model.vo.msg.*;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:32
 */
public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int getLastInsertId();

    void addSpec(List<Spec> specList);

    GoodsInfoVO getGoods(String id);

    List<SpecInfoVO> getSpecs(String id);

    void addSpec(AddSpec addSpec);

    void deleteSpec(DeleteSpecBO deleteSpecBO);

    void updateGoods(Goods goods);

    void updateSpec(List<UpdateSpecBO> specList);

    void deleteGoods(String id);

    void deleteSpec(String id);

    void addType(AddTypeBO addTypeBO);

    int deleteType(String typeId);

    List<Message> noReplyMsg();

    GoodsMsg getGoodsMsg(Integer goodsId);

    UserMsg getUserMsg(Integer userId);

    List<Message> repliedMsg();

    void reply(ReplyBO replyBO);

    List<SearchGoodsVO> searchGoods(String keyword);

    UserGoodsInfoVO getGoodsInfo(String id);

    List<AskVO> getAskInfo(String id);

    ReplyVO getReplyInfo(Integer id);

    List<Comment> getGoodsComments(String goodsId);

    UserNickname getNickname(Integer userId);

    GetUserIdByNameVO getUserIdByName(String token);

    void askGoodsMsg(AskGoodsMsgBO askGoodsMsgBO, Integer id);
}
