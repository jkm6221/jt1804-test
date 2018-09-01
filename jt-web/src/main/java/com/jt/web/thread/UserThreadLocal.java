package com.jt.web.thread;

import com.jt.web.pojo.User;

public class UserThreadLocal {
    /**
     * 关于参数的说明
     *  如果需要传递多值
     *  泛型不行啊，用map集合封装 ThreadLocal<Map<String,String>> 
     *  
     *  注意 GC回收不了threadLocal的东西 它是线程的不是对象的
     */
    private static ThreadLocal<User> userThread = new ThreadLocal<User>();

    public static void set(User user) {
        userThread.set(user);
    }
    
    public static User get() {
        return userThread.get();
    }

    public static void remove() {
        userThread.remove();
    }
    
}
