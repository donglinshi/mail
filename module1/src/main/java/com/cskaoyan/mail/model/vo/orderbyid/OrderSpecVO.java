package com.cskaoyan.mail.model.vo.orderbyid;

/**
 * @author 史栋林
 * @date 2020/8/9 21:29
 */
public class OrderSpecVO {

    private Integer id;

    private String specName;

    private String unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
