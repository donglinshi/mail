package com.cskaoyan.mail.service;

import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;

import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 11:29
 */
public interface OrderService {

    Map<String, Object> ordersByPage(PageOrderBO pageOrderBO);
}
