package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:32
 */
public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);
}
