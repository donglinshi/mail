package com.cskaoyan.mail.service;

import com.cskaoyan.mail.model.AddSpec;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.vo.*;
import com.cskaoyan.mail.model.vo.msg.CommentList;
import com.cskaoyan.mail.model.vo.msg.GetMsgInfo;
import com.cskaoyan.mail.model.vo.msg.NoReplyMsgVO;
import com.cskaoyan.mail.model.vo.msg.RepliedMsgVO;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:24
 */
public interface GoodsService {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(AddGoodsBO addGoodsBO);

    GoodsInfoVO getGoods(String id);

    List<SpecInfoVO> getSpecs(String id);

    void addSpec(AddSpec addSpec);

    void deleteSpec(DeleteSpecBO deleteSpecBO);

    void updateGoods(UpdateGoodsBO updateGoodsBO);

    void deleteGoods(String id);

    void addType(AddTypeBO addTypeBO);

    int deleteType(String typeId);

    List<NoReplyMsgVO> noReplyMsg();

    List<RepliedMsgVO> repliedMsg();

    void reply(ReplyBO replyBO);

    //前端的搜索
    List<SearchGoodsVO> searchGoods(String keyword);

    //用户获取指定商品信息
    UserGoodsInfoVO getGoodsInfo(String id);

    List<GetMsgInfo> getGoodsMsg(String id);

    List<CommentList> getGoodsComments(String goodsId);

    void askGoodsMsg(AskGoodsMsgBO askGoodsMsgBO);
}
