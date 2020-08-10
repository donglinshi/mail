package com.cskaoyan.mail.model.vo.msg;

import java.sql.Date;

/**
 * @author 史栋林
 * @date 2020/8/10 14:36
 * 未回复留言列表VO
 */
public class NoReplyMsgVO {

    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private Integer state;

    private String createtime;

    private GoodsMsg goods;

    private UserMsg user;

    public NoReplyMsgVO() {
    }

    public NoReplyMsgVO(Integer id, Integer userId, Integer goodsId, String content, Integer state, String createtime, GoodsMsg goods, UserMsg user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.state = state;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public GoodsMsg getGoods() {
        return goods;
    }

    public void setGoods(GoodsMsg goods) {
        this.goods = goods;
    }

    public UserMsg getUser() {
        return user;
    }

    public void setUser(UserMsg user) {
        this.user = user;
    }
}
