package com.cskaoyan.mail.service;

import com.cskaoyan.mail.model.AddSpec;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.AddGoodsBO;
import com.cskaoyan.mail.model.bo.AddTypeBO;
import com.cskaoyan.mail.model.bo.DeleteSpecBO;
import com.cskaoyan.mail.model.bo.UpdateGoodsBO;
import com.cskaoyan.mail.model.vo.GoodsInfoVO;
import com.cskaoyan.mail.model.vo.SpecInfoVO;
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

    GoodsInfoVO getGoods(String id);

    List<SpecInfoVO> getSpecs(String id);

    void addSpec(AddSpec addSpec);

    void deleteSpec(DeleteSpecBO deleteSpecBO);

    void updateGoods(UpdateGoodsBO updateGoodsBO);

    void deleteGoods(String id);

    void addType(AddTypeBO addTypeBO);

    int deleteType(String typeId);
}
