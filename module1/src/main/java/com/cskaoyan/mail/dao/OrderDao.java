package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.Goods;
import com.cskaoyan.mail.model.Spec;
import com.cskaoyan.mail.model.bo.ChangeOrderBO;
import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.bo.SettleAccountsBO;
import com.cskaoyan.mail.model.bo.ShoppingCartBO;
import com.cskaoyan.mail.model.vo.GetOrderInfoByStateFromOrder;
import com.cskaoyan.mail.model.vo.HasComments;
import com.cskaoyan.mail.model.vo.PageOrdersVO;
import com.cskaoyan.mail.model.vo.UserInfoVO;
import com.cskaoyan.mail.model.vo.orderbyid.OrderByIdVO;
import com.cskaoyan.mail.model.vo.orderbyid.OrderSpecVO;

import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 14:42
 */
public interface OrderDao {

    List<PageOrdersVO> getOrderByPage(PageOrderBO pageOrderBO);

    Integer getTotalCount(PageOrderBO pageOrderBO);

    OrderByIdVO getOrder(String id);

    List<OrderSpecVO> getOrderSpec(Integer id);//商品的id

    void changeOrder(ChangeOrderBO changeOrderBO);

    void deleteOrder(String id);

    UserInfoVO getUserInfo(String token);

    Spec getSpecInfo(Integer id);

    Goods getGoods(Integer goodsId);

    int addCartOrder(Spec spec, UserInfoVO user, Goods goods, ShoppingCartBO shoppingCartBO);

    List<GetOrderInfoByStateFromOrder> getOrderInfo(String state, String token);

    List<HasComments> hasComments(Integer userId, Integer goodsId);

    void pay(SettleAccountsBO account);

    void confirm(String id,Integer state);

}
