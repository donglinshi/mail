package com.cskaoyan.mail.model.vo;

/**
 * @author 史栋林
 * @date 2020/8/11 21:45
 */
public class GoodsInfoByState {

    private Integer id;

    private String img;

    private String name;

    private Integer goodsDetailId;

    private String spec;

    private Double unitPrice;

    public GoodsInfoByState() {
    }

    public GoodsInfoByState(Integer id, String img, String name, Integer goodsDetailId, String spec, Double unitPrice) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.goodsDetailId = goodsDetailId;
        this.spec = spec;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
