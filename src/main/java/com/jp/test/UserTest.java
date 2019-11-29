package com.jp.test;

import com.jp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: ssm_hps
 * @description: 用户测试
 * @author: CoderPengJiang
 * @create: 2019-11-29 20:43
 **/
public class UserTest {
    public static void main(String[] args) {
        ApplicationContext con=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService as = (UserService) con.getBean("userService");
        System.out.println(as.find(3));
    }
}
