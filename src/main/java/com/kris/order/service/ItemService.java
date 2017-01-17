package com.kris.order.service;

import com.kris.order.pojo.TbItem;

/**
 * @author kris
 * @create 2016-12-12 15:44
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
}
