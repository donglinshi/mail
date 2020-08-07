package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.AddSpec;
import com.cskaoyan.mail.model.Goods;
import com.cskaoyan.mail.model.Spec;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.DeleteSpecBO;
import com.cskaoyan.mail.model.bo.UpdateSpecBO;
import com.cskaoyan.mail.model.vo.GoodsInfoVO;
import com.cskaoyan.mail.model.vo.SpecInfoVO;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:32
 */
public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int getLastInsertId();

    void addSpec(List<Spec> specList);

    GoodsInfoVO getGoods(String id);

    List<SpecInfoVO> getSpecs(String id);

    void addSpec(AddSpec addSpec);

    void deleteSpec(DeleteSpecBO deleteSpecBO);

    void updateGoods(Goods goods);

    void updateSpec(List<UpdateSpecBO> specList);

    void deleteGoods(String id);

    void deleteSpec(String id);
}
