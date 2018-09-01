package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.Order;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private HttpClientService httpClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String saveOrder(Order order) {
        //url                                                    rest风格
        String url ="http://order.jt.com/order/create";
        String orderJSON=null;
        String orderId =null;
        try {
            orderJSON = objectMapper.writeValueAsString(order);
            //添加参数
            Map<String,String> params = new HashMap<>();
            params.put("orderJSON", orderJSON+"");
            
            String orderIdJSON =httpClient.post(url,params); 
            orderId=objectMapper.readValue(orderIdJSON, String.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return orderId;
    }

    @Override
    public Order findOrderById(String id) {
        //order.jt.com/query/......
        System.out.println("OrderServiceImpl：findOrderById：这里的请求没写");
        return null;
    }
    
}
