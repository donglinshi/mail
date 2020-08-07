package com.cskaoyan.mail.model.vo;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 20:06
 */
public class GoodsAndSpecInfoVO {

    private GoodsInfoVO goods;

    private List<SpecInfoVO> specs;

    public GoodsAndSpecInfoVO() {
    }

    public GoodsAndSpecInfoVO(GoodsInfoVO goods, List<SpecInfoVO> specs) {
        this.goods = goods;
        this.specs = specs;
    }

    public GoodsInfoVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfoVO goods) {
        this.goods = goods;
    }

    public List<SpecInfoVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecInfoVO> specs) {
        this.specs = specs;
    }
}
