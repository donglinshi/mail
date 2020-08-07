package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.Type;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
}
