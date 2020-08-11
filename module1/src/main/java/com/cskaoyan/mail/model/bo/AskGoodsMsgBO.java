package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/11 17:26
 */
public class AskGoodsMsgBO {

    private String token;

    private String msg;

    private Integer goodsId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
