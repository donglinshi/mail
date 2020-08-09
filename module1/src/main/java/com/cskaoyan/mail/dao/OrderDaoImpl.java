package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.bo.ChangeOrderBO;
import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;
import com.cskaoyan.mail.model.vo.SpecInfoVO;
import com.cskaoyan.mail.model.vo.orderbyid.OrderByIdVO;
import com.cskaoyan.mail.model.vo.orderbyid.OrderSpecVO;
import com.cskaoyan.mail.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 14:42
 */
public class OrderDaoImpl implements OrderDao {

    public List<PageOrdersVO> getOrderByPage(PageOrderBO pageOrderBO) {

        //动态的sql查询格式：select xxx,xxx from orders where 1=1 and xxx limit xx offset xxx
        Map map = getDynamicSqlAndParams(pageOrderBO);

        String suffix = (String) map.get("sql");
        List params = (List) map.get("params");

        String sql = "select * from orders "+ suffix + "limit ? offset ?";

        params.add(pageOrderBO.getPagesize());
        params.add((pageOrderBO.getCurrentPage() - 1) * pageOrderBO.getPagesize());

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<PageOrdersVO> ordersVOList = null;
        try {
            ordersVOList = runner.query(sql,new BeanListHandler<PageOrdersVO>(PageOrdersVO.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersVOList;
    }

    /**
     * @description:动态拼接sql语句
     * @params:
     * @return:
     * @author: 史栋林
     */
    private Map getDynamicSqlAndParams(PageOrderBO pageOrderBO) {
        String baseSql = " where 1 = 1 ";
        //保存参数
        List params = new ArrayList();
        if (!StringUtils.isEmpty(pageOrderBO.getAddress())){
            baseSql += " and address like ? ";
            params.add("%" + pageOrderBO.getAddress() + "%");
        }
        if (!StringUtils.isEmpty(pageOrderBO.getGoods())){
            baseSql += " and goods like ? ";
            params.add("%" + pageOrderBO.getGoods() + "%");
        }
        if (!StringUtils.isEmpty(pageOrderBO.getMoneyLimit1())){
            baseSql += " and amount <= ? ";
            params.add(pageOrderBO.getMoneyLimit1());
        }
        if (!StringUtils.isEmpty(pageOrderBO.getMoneyLimit2())){
            baseSql += " and amount >= ?";
            params.add(pageOrderBO.getMoneyLimit2());
        }
        if (!StringUtils.isEmpty(pageOrderBO.getId())){
            baseSql += " and id = ? ";
            params.add(pageOrderBO.getId());
        }
        if (!StringUtils.isEmpty(pageOrderBO.getName())){
            baseSql += " and nickname like ? ";
            params.add("%" +pageOrderBO.getName() + "%");
        }
        if (pageOrderBO.getState() != -1){
            baseSql += " and stateId = ? ";
            params.add(pageOrderBO.getState());
        }
        HashMap<Object,Object> map = new HashMap<Object, Object>();
        map.put("sql",baseSql);
        map.put("params",params);
        return map;
    }

    public Integer getTotalCount(PageOrderBO pageOrderBO) {

        Map map = getDynamicSqlAndParams(pageOrderBO);
        String sql = (String)map.get("sql");
        List params = (List) map.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        //只查询count的值
        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from orders " + sql,new ScalarHandler(),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    public OrderByIdVO getOrder(String id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        OrderByIdVO orderByIdVO = null;
        try {
            orderByIdVO = runner.query("select id,amount,goodsNum as num,goodsDetailId,stateId as state,goods,goodsId from orders where id = ?",
                    new BeanHandler<OrderByIdVO>(OrderByIdVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderByIdVO;
    }

    public List<OrderSpecVO> getOrderSpec(Integer id) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<OrderSpecVO> orderSpecVO = null;
        try {
            orderSpecVO = runner.query("select id,specName,unitPrice from spec where goodsId = ?",
                    new BeanListHandler<OrderSpecVO>(OrderSpecVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderSpecVO;
    }

    public void changeOrder(ChangeOrderBO changeOrderBO) {

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        SpecInfoVO specInfoVO = null;
        try {
            specInfoVO = runner.query("select id, specName, stockNum, unitPrice from spec where id = ?",
                    new BeanHandler<SpecInfoVO>(SpecInfoVO.class),changeOrderBO.getSpec());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            runner.update("update orders set stateId = ?, goodsDetailId = ?, goodsNum = ?,spec = ?,price = ?, amount = ?  where id = ?",
                    changeOrderBO.getState(),
                    changeOrderBO.getSpec(),
                    changeOrderBO.getNum(),
                    specInfoVO.getSpecName(),
                    specInfoVO.getUnitPrice(),
                    specInfoVO.getUnitPrice() * changeOrderBO.getNum(),
                    changeOrderBO.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
