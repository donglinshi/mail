package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.Goods;
import com.cskaoyan.mail.model.Spec;
import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.model.vo.TypeGoodsVO;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
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

}
