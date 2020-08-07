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
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/7 11:33
 */
public class GoodsDaoImpl implements GoodsDao {

    public List<Type> getType() {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> list = null;
        try {
            list = runner.query("select * from type",new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TypeGoodsVO> getGoodsByType(String typeId) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<TypeGoodsVO> voList = null;
        try {
            voList = runner.query("select id,img,name,price,typeId,stockNum from goods where typeId = ?",
                    new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voList;
    }

    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            //返回值标识影响的行数
            runner.update("insert into goods values (null,?,?,?,?,?,?)",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getLastInsertId() {

        //同线程下新插入的id，别的线程不会影响
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = (BigInteger) runner.query("select last_insert_id()",new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query.intValue();
    }

    public void addSpec(List<Spec> specList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specList) {
            try {
                runner.update("insert into spec values (null,?,?,?,?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public GoodsInfoVO getGoods(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        GoodsInfoVO goodsInfoVO = null;
        try {
            goodsInfoVO = runner.query("select id,img,name,`desc`,typeId from goods where id = ?",
                    new BeanHandler<GoodsInfoVO>(GoodsInfoVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsInfoVO;
    }

    public List<SpecInfoVO> getSpecs(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<SpecInfoVO> specInfoVOList = null;
        try {
            specInfoVOList = runner.query("select id,specName,stockNum,unitPrice from spec where goodsId = ?",
                    new BeanListHandler<SpecInfoVO>(SpecInfoVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specInfoVOList;
    }

    /**
     * 编辑商品时的添加规格函数
     * */
    public void addSpec(AddSpec addSpec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into spec values(null,?,?,?,?)",
                    addSpec.getSpecName(),
                    addSpec.getStockNum(),
                    addSpec.getUnitPrice(),
                    addSpec.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpec(DeleteSpecBO deleteSpecBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from spec where goodsId = ? and specName = ?",
                    deleteSpecBO.getGoodsId(),deleteSpecBO.getSpecName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update goods set img = ?,name = ?,price = ?,typeId = ?,stockNum = ?,`desc` = ? where id = ?",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc(),
                    goods.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSpec(List<UpdateSpecBO> specList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (UpdateSpecBO updateSpecBO : specList){
            try {
                runner.update("update spec set specName = ?,stockNum = ?,unitPrice = ? where id = ?",
                        updateSpecBO.getSpecName(),
                        updateSpecBO.getStockNum(),
                        updateSpecBO.getUnitPrice(),
                        updateSpecBO.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteGoods(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from goods where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpec(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from spec where goodsId = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
