package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.GoodsDao;
import com.cskaoyan.mail.dao.GoodsDaoImpl;
import com.cskaoyan.mail.model.Type;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:25
 */
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao = new GoodsDaoImpl();

    public List<Type> getType() {
        return goodsDao.getType();
    }
}
