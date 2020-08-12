package com.cskaoyan.mail.service;

import com.cskaoyan.mail.model.bo.*;
import com.cskaoyan.mail.model.vo.GetOrderByStateVO;

import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 11:29
 */
public interface OrderService {

    Map<String, Object> ordersByPage(PageOrderBO pageOrderBO);


    Map<String, Object> order(String id);

    void changeOrder(ChangeOrderBO changeOrderBO);

    void deleteOrder(String id);

    int addOrder(ShoppingCartBO shoppingCartBO);

    List<GetOrderByStateVO> getOrderByState(String state, String token);

    void settleAccounts(SettleAccountsBO account);

    void confiemRe(String id);

    void confirmPay(String id);

    void sendComment(SendCommentBO sendCommentBO);
}
