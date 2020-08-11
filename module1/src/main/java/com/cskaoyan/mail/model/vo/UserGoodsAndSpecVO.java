package com.cskaoyan.mail.model.vo;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/11 10:53
 *用户获取商品信息
 */
public class UserGoodsAndSpecVO {

    private String img;

    private String name;

    private String desc;

    private Integer typeId;

    private List<SpecInfoVO> specs;

    private Double unitPrice = 0.0;

    public UserGoodsAndSpecVO() {
    }

    public UserGoodsAndSpecVO(String img, String name, String desc, Integer typeId, List<SpecInfoVO> specs) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.typeId = typeId;
        this.specs = specs;
    }
}
