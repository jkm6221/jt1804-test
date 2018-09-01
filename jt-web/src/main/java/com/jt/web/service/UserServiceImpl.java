package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpClientService httpClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveUser(User user) {
        //调sso里的service 返回的是SysResult型的json数据
        Map<String,String> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        params.put("email", user.getPhone());
        String url ="http://sso.jt.com/user/register";//
        String resultJson = httpClient.post(url,params);
        try {
            SysResult sysResult = objectMapper.readValue(resultJson, SysResult.class);
            if(sysResult.getStatus() != 200){
                throw new RuntimeException(); //这个和后面那个throw 都是因为出错了要抛给前台那个trycatch
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public String findUserByUP(User user) {
        Map<String,String> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        String url = "http://sso.jt.com/user/login";
        String resultJson = httpClient.post(url,params);//http协议传String
        String token = null;
        try {
            SysResult sysRessult = objectMapper.readValue(resultJson, SysResult.class);
            if(sysRessult.getStatus()!=200) {
                throw new RuntimeException("状态码不是200！");
            }
            token = (String) sysRessult.getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("findUserByUP：SysResult有问题");
        }
        
        return token;
    }

}
