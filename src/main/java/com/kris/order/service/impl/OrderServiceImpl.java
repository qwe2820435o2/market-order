package com.kris.order.service.impl;

import com.kris.order.component.JedisClient;
import com.kris.order.mapper.TbOrderItemMapper;
import com.kris.order.mapper.TbOrderMapper;
import com.kris.order.mapper.TbOrderShippingMapper;
import com.kris.order.pojo.OrderInfo;
import com.kris.order.pojo.TaotaoResult;
import com.kris.order.pojo.TbOrderItem;
import com.kris.order.pojo.TbOrderShipping;
import com.kris.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author kris
 * @create 2017-01-17 14:17
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private TbOrderMapper mOrderMapper;
    @Autowired
    private TbOrderItemMapper mOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper mOrderShippingMapper;

    @Autowired
    private JedisClient mJedisClient;

    @Value("${REDIS_ORDER_GEN_KEY}")
    private String REDIS_ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${REDIS_ORDER_DETAIL_GEN_KEY}")
    private String REDIS_ORDER_DETAIL_GEN_KEY;

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {
        //插入订单表
        //接收数据OrderInfo
        //生成订单号
        //取订单号
        String id = mJedisClient.get(REDIS_ORDER_GEN_KEY);
        if (StringUtils.isBlank(id)) {
            //如果订单号生成key不存在设置初始值
            mJedisClient.set(REDIS_ORDER_GEN_KEY, ORDER_ID_BEGIN);
        }
        Long orderId = mJedisClient.incr(REDIS_ORDER_GEN_KEY);
        orderInfo.setOrderId(orderId.toString());
        //状态：1.未付款 2.已付款 3.未发货 4.已发货 5.交易成功 6.交易关闭
        orderInfo.setStatus(1);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
        //插入订单表
        mOrderMapper.insert(orderInfo);

        //插入订单明细
        //补全字段
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            //生成订单明细id，使用redis的incr命令生成
            Long detailId = mJedisClient.incr(REDIS_ORDER_DETAIL_GEN_KEY);
            orderItem.setId(detailId.toString());
            //订单号
            orderItem.setOrderId(orderId.toString());
            //插入数据
            mOrderItemMapper.insert(orderItem);
        }

        //插入物流表
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        //补全字段
        orderShipping.setOrderId(orderId.toString());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        //插入数据
        mOrderShippingMapper.insert(orderShipping);
        //返回TaotaoResult包装订单号
        return TaotaoResult.ok(orderId);
    }
}
