package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/9 23:33
 */
public class ChangeOrderBO {

    private Integer id;

    private Integer state;

    private String spec;

    private Integer num;

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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
