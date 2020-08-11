package com.cskaoyan.mail.model.vo;

/**
 * @author 史栋林
 * @date 2020/8/11 21:40
 */
public class GetOrderByStateVO {

    private Integer id;

    private Integer state;

    private Integer goodsNum;

    private Double amount;

    private Integer goodsDetailId;

    private String createtime;

    private Boolean hasComment;

    private GoodsInfoByState goods;

    public GetOrderByStateVO() {
    }

    public GetOrderByStateVO(Integer id, Integer state, Integer goodsNum, Double amount, Integer goodsDetailId, String createtime, Boolean hasComment, GoodsInfoByState goods) {
        this.id = id;
        this.state = state;
        this.goodsNum = goodsNum;
        this.amount = amount;
        this.goodsDetailId = goodsDetailId;
        this.createtime = createtime;
        this.hasComment = hasComment;
        this.goods = goods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Boolean getHasComment() {
        return hasComment;
    }

    public void setHasComment(Boolean hasComment) {
        this.hasComment = hasComment;
    }

    public GoodsInfoByState getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfoByState goods) {
        this.goods = goods;
    }
}
