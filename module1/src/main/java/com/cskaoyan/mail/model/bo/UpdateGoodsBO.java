package com.cskaoyan.mail.model.bo;

import com.cskaoyan.mail.model.AddSpec;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 21:41
 */
public class UpdateGoodsBO {

    private Integer id;

    private String name;

    private Integer typeId;

    private String img;

    private String desc;

    private List<UpdateSpecBO> specList;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<UpdateSpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<UpdateSpecBO> specList) {
        this.specList = specList;
    }
}
