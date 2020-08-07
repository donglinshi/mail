package com.cskaoyan.mail.service;

import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.AddGoodsBO;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:24
 */
public interface GoodsService {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(AddGoodsBO addGoodsBO);
}
