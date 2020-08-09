package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.bo.ChangeOrderBO;
import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;
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
}
