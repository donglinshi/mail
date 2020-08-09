package com.cskaoyan.mail.model.vo.orderbyid;

/**
 * @author 史栋林
 * @date 2020/8/9 21:26
 */
public class OrderStatesVO {

    private Integer id;

    private String name;

    public OrderStatesVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
