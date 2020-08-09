package com.cskaoyan.mail.dao;

import com.cskaoyan.mail.model.bo.PageOrderBO;
import com.cskaoyan.mail.model.vo.PageOrdersVO;

import java.util.List;
import java.util.Map;

/**
 * @author 史栋林
 * @date 2020/8/9 14:42
 */
public interface OrderDao {

    List<PageOrdersVO> getOrderByPage(PageOrderBO pageOrderBO);

    Integer getTotalCount(PageOrderBO pageOrderBO);
}
