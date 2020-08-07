package com.cskaoyan.mail.model.vo;

/**
 * @author 史栋林
 * @date 2020/8/7 17:02
 * unitPrice返回前端并没什么用，
 */
public class GoodsInfoVO {

    private Integer id;

    private String img;

    private String name;

    private String desc;

    private Integer typeId;

    private Double unitPrice = 0.0;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

//    public Double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(Double unitPrice) {
//        this.unitPrice = unitPrice;
//    }


    public GoodsInfoVO() {
    }

    public GoodsInfoVO(Integer id, String img, String name, String desc, Integer typeId) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.typeId = typeId;
    }
}
