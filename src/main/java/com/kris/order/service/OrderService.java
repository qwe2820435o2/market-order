package com.kris.order.service;

import com.kris.order.pojo.OrderInfo;
import com.kris.order.pojo.TaotaoResult;

/**
 * @author kris
 * @create 2017-01-17 14:08
 */
public interface OrderService {
    TaotaoResult createOrder(OrderInfo orderInfo);
}
