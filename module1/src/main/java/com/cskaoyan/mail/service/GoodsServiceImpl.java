package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.GoodsDao;
import com.cskaoyan.mail.dao.GoodsDaoImpl;
import com.cskaoyan.mail.model.Goods;
import com.cskaoyan.mail.model.Spec;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.bo.AddGoodsBO;
import com.cskaoyan.mail.model.bo.SpecBO;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;

import java.util.ArrayList;
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

    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    /**
     * 新增商品表price,stockNum需要通过specList运算得到
     * 1.保存数据到商品表
     * 2.拿到 商品表刚刚插入的id
     * 3.将id以及spec数据保存到规格表
     * */
    public void addGoods(AddGoodsBO addGoodsBO) {
        //处理商品的价格和库存，在商品表中存储的是不同规格的最低价和最大库存
        List<SpecBO> specBOList = addGoodsBO.getSpecList();
        double price = specBOList.get(0).getUnitPrice();
        int stockNum = specBOList.get(0).getStockNum();
        //找到最低价
        for (int i = 1; i < specBOList.size(); i++){
            if (price > specBOList.get(i).getUnitPrice()){
                price = specBOList.get(i).getUnitPrice();
            }
        }
        //获取库存最大值
        for (int i = 1; i < specBOList.size(); i++){
            if (stockNum < specBOList.get(i).getStockNum()){
                stockNum = specBOList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(null,addGoodsBO.getImg(),addGoodsBO.getName(),
                price,addGoodsBO.getTypeId(),stockNum,addGoodsBO.getDesc());

        goodsDao.addGoods(goods);
        //获取最后一次插入的id，用于spec表的更新
        int id = goodsDao.getLastInsertId();
        //保存数据到规格表,添加的可能是多规格的商品
        List<Spec> specList = new ArrayList<Spec>();
        for (SpecBO specBO : specBOList) {
            Spec spec = new Spec(null,
                    specBO.getSpecName(),
                    specBO.getStockNum(),
                    specBO.getUnitPrice(),
                    id);

            specList.add(spec);
        }

        goodsDao.addSpec(specList);


    }
}
