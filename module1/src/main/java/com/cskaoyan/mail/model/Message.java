package com.cskaoyan.mail.model;

import com.cskaoyan.mail.model.vo.msg.GoodsMsg;
import com.cskaoyan.mail.model.vo.msg.UserMsg;

import java.sql.Date;

/**
 * @author 史栋林
 * @date 2020/8/10 14:53
 */
public class Message {

    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private String replyContent;

    private Integer state;

    private Date createtime;

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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}
