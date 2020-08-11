package com.cskaoyan.mail.model.bo;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/12 0:24
 */
public class SettleBO {

    private List<SettleAccountsBO> cartList;

    public List<SettleAccountsBO> getCartList() {
        return cartList;
    }

    public void setCartList(List<SettleAccountsBO> cartList) {
        this.cartList = cartList;
    }
}
