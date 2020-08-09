package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.OrderDao;
import com.cskaoyan.mail.dao.OrderDaoImpl;
import com.cskaoyan.mail.model.bo.ChangeOrderBO;
import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;
import com.cskaoyan.mail.model.vo.orderbyid.*;

import java.util.ArrayList;
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

    public Map<String, Object> order(String id) {

        //先获取订单表的数据
        OrderByIdVO orderByIdVO = orderDao.getOrder(id);
        //先封装一部分数据
        Map<String,Object> map = new HashMap<String, Object>();

        //封装对象型数据
        Integer goodsId =orderByIdVO.getGoodsId();
        List<OrderSpecVO> orderSpecVO = orderDao.getOrderSpec(goodsId);


        List<OrderStatesVO> orderStatesVOS = new ArrayList<OrderStatesVO>();
        orderStatesVOS.add(new OrderStatesVO(0,"未付款"));
        orderStatesVOS.add(new OrderStatesVO(1,"未发货"));
        orderStatesVOS.add(new OrderStatesVO(2,"已发货"));
        orderStatesVOS.add(new OrderStatesVO(3,"已完成订单"));

        //顺序无所谓了，但是别的bug改了，不想改回来了
        map.put("curSpec",new CurSpecVO(orderByIdVO.getGoodsDetailId()));

        map.put("curState",new CurStateVO(orderByIdVO.getState()));

        map.put("states",orderStatesVOS);

        map.put("spec",orderSpecVO);

        map.put("goods",orderByIdVO.getGoods());

        map.put("state",orderByIdVO.getState());

        map.put("goodsDetailId",orderByIdVO.getGoodsDetailId());

        map.put("num",orderByIdVO.getNum());

        map.put("amount",orderByIdVO.getAmount());

        map.put("id",orderByIdVO.getId());

        return map;
    }

    public void changeOrder(ChangeOrderBO changeOrderBO) {
        orderDao.changeOrder(changeOrderBO);
    }

    public void deleteOrder(String id) {
        orderDao.deleteOrder(id);
    }


}
