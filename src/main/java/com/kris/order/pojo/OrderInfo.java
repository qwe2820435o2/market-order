package com.kris.order.pojo;

import java.util.List;

/**
 * TbOrder扩展pojo类
 *
 * @author kris
 * @create 2017-01-17 13:51
 */
public class OrderInfo extends TbOrder{
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
