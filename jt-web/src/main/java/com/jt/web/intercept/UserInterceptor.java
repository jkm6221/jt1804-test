package com.jt.web.intercept;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.pojo.User;
import com.jt.web.thread.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

//@Component
public class UserInterceptor implements HandlerInterceptor{

    @Autowired
    private JedisCluster jedisCluster;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Cookie[] cookies = request.getCookies();
        String token="";
        for (Cookie cookie : cookies) {
            if("JT_TICKET".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        if(!StringUtils.isEmpty(token)) {
            //检查缓存中是否有该数据
            String userJSON = jedisCluster.get(token);
            if(!StringUtils.isEmpty(userJSON)) {
                //将userJSon转化为user对象
                User user = objectMapper.readValue(userJSON, User.class);
                //思路 使用session共享数据
                //request.getSession().setAttribute("JT_USER", user);
                
                //存到ThreadLocal里去
                UserThreadLocal.set(user);
                //用户已经登录
                return true;
            }
        }
        //表示用户没有登录
        response.sendRedirect("/user/login.html");
        
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserThreadLocal.remove();
        
    }

}
