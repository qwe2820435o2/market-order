package com.kris.order.controller;

import com.kris.order.pojo.OrderInfo;
import com.kris.order.pojo.TaotaoResult;
import com.kris.order.service.OrderService;
import com.kris.order.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单接口
 *
 * @author kris
 * @create 2017-01-17 15:09
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService mOrderService;

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody OrderInfo orderInfo) {
        try {
            TaotaoResult result = mOrderService.createOrder(orderInfo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
