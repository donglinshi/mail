package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/11 23:24
 */
public class SettleAccountsBO {

    private Integer id;

    private Integer goodsNum;

    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
