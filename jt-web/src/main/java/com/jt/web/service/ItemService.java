package com.jt.web.service;

import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

public interface ItemService {

    Item findItemById(String itemId);

    ItemDesc findItemDescById(String itemId);

}
