package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private HttpClientService httpClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    //前台调后台的数据库数据?
    @Override
    public Item findItemById(String itemId) {
        //远程调用后台?使用HttpClient HttpClientservice 
        //url                                                    rest风格
        String url ="http://manage.jt.com/web/item/findItemById";
        //添加参数
        Map<String,String> params = new HashMap<>();
        params.put("itemId", itemId+"");
        
        String itemJSON =httpClient.get(url,params);
        System.out.println("findItemById():itemJSON:"+itemJSON);
        Item item = null;
        try {
            item = objectMapper.readValue(itemJSON, Item.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        return item;
    }
    @Override
    public ItemDesc findItemDescById(String itemId) {
        //远程调用后台?使用HttpClient HttpClientservice 
        //url                                                    rest风格
        String url ="http://manage.jt.com/web/item/findItemDescById";
        //添加参数
        Map<String,String> params = new HashMap<>();
        params.put("itemId", itemId+"");
        
        String itemDescJSON = httpClient.get(url,params);
        ItemDesc itemDesc = null;
        
        try {
            itemDesc = objectMapper.readValue(itemDescJSON, ItemDesc.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemDesc;
    }

}
