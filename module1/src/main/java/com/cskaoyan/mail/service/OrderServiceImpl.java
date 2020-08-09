package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.OrderDao;
import com.cskaoyan.mail.dao.OrderDaoImpl;
import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 11:30
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * @description:根据不同的参数，执行不同的查询，返回相应的查询结果
     * @params:
     * @author: 史栋林
     */
    public Map<String,Object> ordersByPage(PageOrderBO pageOrderBO) {

        //分页查询订单结果
        Integer count = orderDao.getTotalCount(pageOrderBO);
        List<PageOrdersVO> pageOrderVOList = orderDao.getOrderByPage(pageOrderBO);
        Map map = new HashMap();
        map.put("total",count);
        map.put("orders",pageOrderVOList);
        return map;
    }
}
