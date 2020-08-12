package com.cskaoyan.mail.service;

import com.cskaoyan.mail.dao.OrderDao;
import com.cskaoyan.mail.dao.OrderDaoImpl;
import com.cskaoyan.mail.model.Goods;
import com.cskaoyan.mail.model.Spec;
import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.vo.*;
import com.cskaoyan.mail.model.vo.orderbyid.*;

import java.text.SimpleDateFormat;
import java.util.*;

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

    public int addOrder(ShoppingCartBO shoppingCartBO) {
        //先查询规格信息
        Spec spec = orderDao.getSpecInfo(shoppingCartBO.getGoodsDetailId());
        if (spec.getStockNum() <= 0 || shoppingCartBO.getNum() > spec.getStockNum()){
            return -1;
        }
        UserInfoVO user = orderDao.getUserInfo(shoppingCartBO.getToken());
        Goods goods = orderDao.getGoods(spec.getGoodsId());

        //需要更新库存
        orderDao.updateStockNum(shoppingCartBO.getGoodsDetailId(),spec.getStockNum() - shoppingCartBO.getNum());

        int code = orderDao.addCartOrder(spec,user,goods,shoppingCartBO);

        return code;
    }

    public List<GetOrderByStateVO> getOrderByState(String state, String token) {
        //获取订单表中的相应信息
        List<GetOrderInfoByStateFromOrder> lists = orderDao.getOrderInfo(state,token);
        Boolean hasComments = true;
        List<GetOrderByStateVO> listVO = new ArrayList<GetOrderByStateVO>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (GetOrderInfoByStateFromOrder order : lists){
            //先判断是否有有评论
            List<HasComments> comments = orderDao.hasComments(order.getUserId(),order.getGoodsId());
            if (comments.isEmpty()){
                hasComments = false;
            }
            //查询商品相关信息

            Goods goods = orderDao.getGoods(order.getGoodsId());
            Spec spec = orderDao.getSpecInfo(order.getGoodsDetailId());
            GoodsInfoByState goodsInfo = new GoodsInfoByState(goods.getId(),goods.getImg(),goods.getName(),order.getGoodsDetailId(),spec.getSpecName(),spec.getUnitPrice());
            listVO.add(new GetOrderByStateVO(order.getId(),
                    order.getState(),
                    order.getGoodsNum(),
                    order.getAmount(),
                    order.getGoodsDetailId(),
                    simpleDateFormat.format(order.getCreatetime()),
                    hasComments,
                    goodsInfo));
        }

        return listVO;
    }

    public void settleAccounts(SettleAccountsBO account) {
        orderDao.pay(account);
    }

    public void confiemRe(String id) {
        Integer state = 3;
        orderDao.confirm(id,state);
    }

    public void confirmPay(String id) {
        Integer state = 1;
        orderDao.confirm(id,state);
    }

    public void sendComment(SendCommentBO sendCommentBO) {
        //先查询订单是否存在以及相关信息
        OrderSendCommentInfo info = orderDao.getOrderSpecName(sendCommentBO.getOrderId());

        orderDao.sendComment(sendCommentBO,info);
    }


}
