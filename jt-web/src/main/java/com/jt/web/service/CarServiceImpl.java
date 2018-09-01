package com.jt.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;

@Service
public class CarServiceImpl implements CartService{

    @Autowired
    private HttpClientService httpClient;
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        String url = "http://cart.jt.com/cart/query/"+userId;
        String resultJSON = httpClient.get(url);
        List<Cart> list = new ArrayList<>(); 
        try {
            SysResult sysResult = objectMapper.readValue(resultJSON, SysResult.class);
            //                  本体是linkedHashmap可以强转list
            list = (List<Cart>)  sysResult.getData();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public void saveCart(Cart cart) {
        String url="http://cart.jt.com/cart/save";
        Map<String,String> params = new HashMap<>();
        try {
            String cartJson = objectMapper.writeValueAsString(cart);
            params.put("cartJson", cartJson);
            System.out.println("saveCart"+cartJson);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpClient.post(url,params);
        System.out.println("购物车入库成功！！");
    }

    @Override
    public void updateCartNum(Cart cart) {
        String url = "http://cart.jt.com/cart/update/num/"+cart.getUserId()+"/"+cart.getItemId()+"/"+cart.getNum();
        httpClient.post(url);
    }

}
