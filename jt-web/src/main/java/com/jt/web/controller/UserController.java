package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JedisCluster jedisCluster;
    
    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName) {
        return moduleName;
    }
    
    //http://www.jt.com/service/user/doRegister用户注册service 帮你拼着了 拦截的时候
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult saveUser(User user) {
        try {
            userService.saveUser(user);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户新增失败");
    }
    //实现用户登录http://www.jt.com/service/user/doLogin?r=0.4173365862850422
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult findUserByUP(User user,HttpServletResponse response) {
        try {
            //获取加密后的秘钥
            String token = userService.findUserByUP(user);
            
            //判断token是否为null
            if(StringUtils.isEmpty(token)) {
                throw new RuntimeException("findUserByUP：取到的token有问题");
            }
            //将token写入客户端cookie中 cookie最大4k
            Cookie cookie = new Cookie("JT_TICKET", token);
            cookie.setPath("/");//保存到根目录
            cookie.setMaxAge(60*60*24*7);//最大的生命周期 单位秒 写0的话立即销毁cookie，用于删cookie -1是会话关闭后cookie销毁
            response.addCookie(cookie);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户登录失败");
    }
    //http://www.jt.com/user/logout.html登出
    /**
     * 获取cookie数据
     * 删除redis缓存
     * 删除cookie
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if("JT_TICKET".equals(cookie.getName())) {
               token = cookie.getValue();
               break;
            }
        }
        jedisCluster.del(token);//删除缓存
        Cookie cookie = new Cookie("JT_TICKET", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);//覆盖删cookie
        return "redirect:/index.html";
    }
}